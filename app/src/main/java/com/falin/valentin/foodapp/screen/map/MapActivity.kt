package com.falin.valentin.foodapp.screen.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.MapActivityViewModelFactoryModule
import com.falin.valentin.foodapp.models.domain.Pickup
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.dialog.RationaleDialogFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.utils.isPermissionGranted
import com.falin.valentin.foodapp.utils.requestPermission
import com.falin.valentin.foodapp.viewmodel.MapActivityViewModel
import com.falin.valentin.foodapp.viewmodel.factories.MapActivityViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MapActivity : BaseActivity(), OnMapReadyCallback {
    override val owner: Logger.Owner
        get() = Logger.Owner.MAP_ACTIVITY

    private lateinit var map: GoogleMap
    private var lastKnownLocation: Location? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var permissionDenied = false

    @Inject
    lateinit var factory: MapActivityViewModelFactory
    private lateinit var viewModel: MapActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        initDagger()
        viewModel = injectViewModel(factory)
        initViews()
        initLiveData()
    }

    override fun onResume() {
        super.onResume()
        if (permissionDenied) {
            showMissingPermissionError()
            permissionDenied = false
        }
    }

    override fun onBackPressed() {
        if (activity_map_pickup_root_layout.isVisible) {
            activity_map_pickup_root_layout.isVisible = false
        } else {
            super.onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != ACCESS_LOCATION_PERMISSION_REQUEST) {
            return
        }
        if (isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            enableMyLocation()
        } else {
            permissionDenied = true
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMyLocationButtonClickListener(::onMyLocationButtonClick)
        map.setOnMyLocationClickListener(::onMyLocationClick)
        map.setOnMarkerClickListener(::onMarkerClick)
        map.uiSettings.isZoomControlsEnabled = true
        enableMyLocation()
    }

    private fun onMyLocationClick(location: Location) {}

    private fun onMyLocationButtonClick(): Boolean {
        return false
    }

    private fun onMarkerClick(marker: Marker): Boolean {
        val pickup = viewModel.getPickup(marker)
        if (pickup != null) {
            getDeviceLocation()
            activity_map_pickup_title_text.text = pickup.name
            activity_map_work_time_text.text = pickup.workingHours
            activity_map_pickup_root_layout.visibility = View.VISIBLE
            val fromLocation = Geocoder(this, Locale.getDefault()).getFromLocation(
                marker.position.latitude,
                marker.position.longitude,
                1
            )
            if (fromLocation != null && fromLocation.size > 0) {
                activity_map_direction_text.text = fromLocation[0].getAddressLine(0)
                activity_map_direction_text.append(
                    if (fromLocation[0].postalCode != null) ", ${fromLocation[0].postalCode}" else ""
                )
            }
            activity_map_pickup_distance_text.text = viewModel.getDistance(lastKnownLocation, marker.position)
        }
        return false
    }

    private fun initViews() {
        initToolbar()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.activity_map_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initLiveData() {
        viewModel.pickupsLiveData.observe(this, Observer { response ->
            if (response.data != null) {
                showMarkers(response.data)
            }
            if (response.error != null) {
                Snackbar.make(
                    activity_map_root_layout, response.error.localizedMessage,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun initToolbar() {
        custom_toolbar.title = getString(R.string.activity_map_toolbar_title)
        setSupportActionBar(custom_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        custom_toolbar.navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
    }

    private fun showMissingPermissionError() {
        RationaleDialogFragment.newInstance(finishOnCancel = true)
            .show(supportFragmentManager, "dialog")
    }

    private fun showMarkers(markers: List<Pickup>) {
        markers.forEach {
            val latLng = LatLng(it.location.latitude, it.location.longitude)
            map.addMarker(MarkerOptions().position(latLng).title(it.name)).also { marker ->
                marker.tag = it.id
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }

    private fun getDeviceLocation() {
        try {
            val permissionResult =
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        lastKnownLocation = task.result
                    } else {
                        Timber.e("Current location is null. Using defaults. ${task.exception}")
                    }
                }
            }
        } catch (e: SecurityException) {
            Timber.e("MapActivity Exception: ${e.message}")
        }
    }

    private fun enableMyLocation() {
        val permissionResult =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionResult == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
//            viewModel.getPickups()
            getDeviceLocation()
        } else {
            requestPermission(
                this, ACCESS_LOCATION_PERMISSION_REQUEST,
                Manifest.permission.ACCESS_FINE_LOCATION, true
            )
        }
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initMapActivityComponent(MapActivityViewModelFactoryModule()).inject(this)
    }

    companion object {
        private const val ACCESS_LOCATION_PERMISSION_REQUEST = 30040
    }
}