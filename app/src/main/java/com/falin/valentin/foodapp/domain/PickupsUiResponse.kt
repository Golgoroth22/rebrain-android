package com.falin.valentin.foodapp.domain

import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupsResponse

/**
 * This is a data class for work with [MapActivity].
 *
 * @param data data of our object.
 * @param isLoading display request loading status.
 * @param error display error.
 *
 */
data class PickupsUiResponse(
    val data: PickupsResponse? = null,
    val isLoading: Boolean,
    val error: Throwable? = null
)

