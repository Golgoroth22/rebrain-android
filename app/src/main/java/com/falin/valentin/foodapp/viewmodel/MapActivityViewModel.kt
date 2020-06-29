package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.models.domain.Pickup
import com.falin.valentin.foodapp.models.ui.PickupsUiResponse
import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupResponse
import com.falin.valentin.foodapp.repository.MapActivityRepository
import com.google.android.gms.maps.model.Marker
import timber.log.Timber

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
        mPickupsLiveData.postValue(
            PickupsUiResponse(
                isLoading = true
            )
        )
        repository.getPickups(
            { response -> receiveSuccessfulResponse(response) },
            { throwable -> receiveFailureResponse(throwable) })
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
}