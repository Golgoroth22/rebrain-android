package com.falin.valentin.foodapp.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falin.valentin.foodapp.models.domain.Pickup
import com.falin.valentin.foodapp.models.ui.PickupsUiResponse
import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupResponse
import com.falin.valentin.foodapp.repository.MapActivityRepository
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import timber.log.Timber
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property pickupsLiveData Property for contain pickups. Wrapped in [LiveData]
 */
class MapActivityViewModel(private val repository: MapActivityRepository) : ViewModel() {
    private val mPickupsLiveData = MutableLiveData<PickupsUiResponse>()
    val pickupsLiveData: LiveData<PickupsUiResponse> = mPickupsLiveData

    /**
     * This method can be called for get pickups.
     *
     */
    fun getPickups() {
        mPickupsLiveData.postValue(PickupsUiResponse(isLoading = true))
        viewModelScope.launch {
            withContext(this.coroutineContext + Dispatchers.IO) {
                repository.getPickups(
                    { response -> receiveSuccessfulResponse(response) },
                    { throwable -> receiveFailureResponse(throwable) })
            }
        }
    }

    /**
     * This method can be called for find pickup.
     *
     *@param marker Selected marker
     */
    fun getPickup(marker: Marker): Pickup? {
        return mPickupsLiveData.value?.data?.find { it.id == marker.tag }
    }

    private fun receiveSuccessfulResponse(pickupsResponse: List<PickupResponse>) {
        val pickups = mutableListOf<Pickup>()
        pickupsResponse.forEach { pickups.add(it.convert()) }
        mPickupsLiveData.postValue(
            PickupsUiResponse(
                pickups,
                isLoading = false
            )
        )
        Timber.i("MapActivityViewModel receiveSuccessfulResponse $pickupsResponse")
    }

    private fun receiveFailureResponse(t: Throwable) {
        mPickupsLiveData.postValue(
            PickupsUiResponse(
                isLoading = false,
                error = t
            )
        )
        Timber.e("MapActivityViewModel receiveFailureResponse ${t.message}")
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