package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupsResponse
import com.falin.valentin.foodapp.network.retrofit.service.PickupsService
import com.falin.valentin.foodapp.network.retrofit.service.RetrofitCallback

/**
 * Repository-layer class for work with pickups.
 *
 */
class MapActivityRepository(private val service: PickupsService) {

    /**
     * This method can be called for take pickups.
     *
     */
    fun getPickups(
        onSuccess: (PickupsResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        service.getPickups().enqueue(RetrofitCallback<PickupsResponse>(onSuccess, onFailure))
    }
}