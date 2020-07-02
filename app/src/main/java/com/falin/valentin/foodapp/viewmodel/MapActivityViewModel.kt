package com.falin.valentin.foodapp.viewmodel

import android.location.Location
import androidx.lifecycle.*
import com.falin.valentin.foodapp.models.domain.Pickup
import com.falin.valentin.foodapp.models.ui.PickupsUiResponse
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
class MapActivityViewModel(private val repository: MapActivityRepository) : ViewModel() {
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
     * This method can be called for find pickup.
     *
     *@param marker Selected marker
     */
    fun getPickup(marker: Marker): Pickup? {
        return pickupsLiveData.value?.data?.find { it.id == marker.tag }
    }

    fun getDistance(firstLocation: Location?, secondLocation: LatLng): String {
        return if (firstLocation != null) {
            val x1 = firstLocation.latitude
            val x2 = secondLocation.latitude
            val y1 = firstLocation.longitude
            val y2 = secondLocation.longitude
            String.format("%.3fм", sqrt((x2 - x1).pow(2.0) + (y2 - y1).pow(2.0)) * 1000)
        } else {
            ""
        }
    }
}