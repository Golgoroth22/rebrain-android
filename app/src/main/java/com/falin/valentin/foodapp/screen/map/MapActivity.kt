package com.falin.valentin.foodapp.screen.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.MapActivityViewModelFactoryModule
import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupsResponse
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.RationaleDialog.Companion.newInstance
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.utils.isPermissionGranted
import com.falin.valentin.foodapp.utils.requestPermission
import com.falin.valentin.foodapp.viewmodel.MapActivityViewModel
import com.falin.valentin.foodapp.viewmodel.factories.MapActivityViewModelFactory
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject


class MapActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationClickListener,
    GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMarkerClickListener {
    override val owner: Logger.Owner
        get() = Logger.Owner.MAP_ACTIVITY

    private lateinit var map: GoogleMap
    private var permissionDenied = false

    @Inject
    lateinit var factory: MapActivityViewModelFactory
    private lateinit var viewModel: MapActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        initDagger()
        viewModel = injectViewModel(factory)
        initViews()
        initLiveData()
    }

    override fun onBackPressed() {
        if (activity_map_pickupRootLayout.visibility == View.VISIBLE) {
            activity_map_pickupRootLayout.visibility = View.GONE
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
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        map.setOnMarkerClickListener(this)
        map.uiSettings.isZoomControlsEnabled = true
        enableMyLocation()
    }

    override fun onMyLocationClick(location: Location) {}

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (permissionDenied) {
            showMissingPermissionError()
            permissionDenied = false
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val pickup = viewModel.getPickup(marker)
        if (pickup != null) {
            activity_map_pickupTitleText.text = pickup.name
            activity_map_workTimeText.text = pickup.workingHours
            activity_map_pickupRootLayout.visibility = View.VISIBLE
        }
        return false
    }

    private fun initViews() {
        initToolbar()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.activity_map_mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initLiveData() {
        viewModel.pickupsLiveData.observe(this, Observer { response ->
            if (response.data != null) {
                viewModel.pickups = response.data
                showMarkers(response.data)
            }
            if (response.error != null) {
                Snackbar.make(
                    activity_map_rootLayout, response.error.localizedMessage,
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
        newInstance(finishActivity = true).show(supportFragmentManager, "dialog")
    }

    private fun showMarkers(markers: PickupsResponse) {
        markers.data.forEach {
            val sydney = LatLng(it.location.lat, it.location.lon)
            map.addMarker(MarkerOptions().position(sydney).title(it.name)).also { marker ->
                marker.tag = it.id
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
            viewModel.getPickups()
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