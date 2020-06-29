package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.UserStorage
import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupResponse
import com.falin.valentin.foodapp.network.retrofit.service.PickupsService
import com.falin.valentin.foodapp.network.retrofit.service.RetrofitCallback

/**
 * Repository-layer class for work with pickups.
 *
 */
class MapActivityRepository(private val storage: UserStorage<String>,
                            private val service: PickupsService) {

    /**
     * This method can be called for take pickups.
     *
     */
    fun getPickups(
        onSuccess: (List<PickupResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        service.getPickups(storage.getUserToken()).enqueue(RetrofitCallback<List<PickupResponse>>(onSuccess, onFailure))
    }
}