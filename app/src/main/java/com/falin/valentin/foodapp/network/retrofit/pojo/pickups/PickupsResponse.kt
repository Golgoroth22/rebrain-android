package com.falin.valentin.foodapp.network.retrofit.pojo.pickups

import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [PickupsResponse].
 *
 * @property data Display list of [PickupResponse]
 */
data class PickupsResponse(
    @Json(name = "data") val data: List<PickupResponse>
)