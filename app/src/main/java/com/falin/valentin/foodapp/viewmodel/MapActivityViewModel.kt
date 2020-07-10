package com.falin.valentin.foodapp.viewmodel

import android.location.Location
import androidx.lifecycle.*
import com.falin.valentin.foodapp.models.domain.Pickup
import com.falin.valentin.foodapp.models.ui.PickupInfoUi
import com.falin.valentin.foodapp.models.ui.PickupsUiResponse
import com.falin.valentin.foodapp.repository.GeoInfoRepository
import com.falin.valentin.foodapp.repository.MapActivityRepository
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import timber.log.Timber
import java.lang.Exception
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property pickupsLiveData Property for contain pickups. Wrapped in [LiveData]
 */
class MapActivityViewModel(
    private val repository: MapActivityRepository,
    private val geoInfoRepository: GeoInfoRepository
) : ViewModel() {
    private val mPickupInfoLiveData = MutableLiveData<PickupInfoUi>()
    val pickupInfoLiveData: LiveData<PickupInfoUi> = mPickupInfoLiveData
    val pickupsLiveData: LiveData<PickupsUiResponse> = liveData {
        emit(PickupsUiResponse(isLoading = true))
        try {
            val result = repository.getPickups()
                .map { it.convert() }
            emit(PickupsUiResponse(result, isLoading = false))
            Timber.i("MapActivityViewModel receiveSuccessfulResponse $result")
        } catch (e: Exception) {
            emit(PickupsUiResponse(isLoading = false, error = e))
            Timber.e("MapActivityViewModel receiveFailureResponse ${e.localizedMessage}")
        }
    }

    /**
     * This method can be called for create pickup info.
     *
     * @param marker Selected marker
     * @param lastKnownLocation Last device location
     */
    fun markerClicked(
        marker: Marker,
        lastKnownLocation: Location?
    ) {
        val pickup = getPickup(marker)
        val pickupInfo = PickupInfoUi()
        if (pickup != null) {
            pickupInfo.name = pickup.name
            pickupInfo.workingHours = pickup.workingHours
            pickupInfo.address = geoInfoRepository.getAddress(marker)
            if (lastKnownLocation != null) {
                pickupInfo.distance = getDistance(lastKnownLocation, marker.position)
            }
        }
        mPickupInfoLiveData.postValue(pickupInfo)
    }

    private fun getPickup(marker: Marker): Pickup? {
        return pickupsLiveData.value?.data?.find { it.id == marker.tag }
    }

    private fun getDistance(firstLocation: Location, secondLocation: LatLng): Int {
        val x1 = firstLocation.latitude
        val x2 = secondLocation.latitude
        val y1 = firstLocation.longitude
        val y2 = secondLocation.longitude
        return (sqrt((x2 - x1).pow(2.0) + (y2 - y1).pow(2.0)) * 1000).toInt()
    }
}