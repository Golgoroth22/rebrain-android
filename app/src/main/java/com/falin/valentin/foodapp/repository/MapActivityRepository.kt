package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.UserStorage
import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupResponse
import com.falin.valentin.foodapp.network.retrofit.service.PickupsService
import java.lang.Exception

/**
 * Repository-layer class for work with pickups.
 *
 */
class MapActivityRepository(
    private val storage: UserStorage<String>,
    private val service: PickupsService
) {

    /**
     * This method can be called to take pickups.
     *
     */
    suspend fun getPickups(
        onSuccess: (List<PickupResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        try {
            val response = service.getPickups(storage.getUserToken())
            onSuccess.invoke(response)
        } catch (e: Exception) {
            onFailure.invoke(e)
        }
    }
}