package com.falin.valentin.foodapp.network.retrofit.pojo.pickups

import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [LatLngResponse].
 *
 * @property lat Display latitude
 * @property lon Display longitude
 */
data class LatLngResponse(
    @Json(name = "lat") val lat: Double,
    @Json(name = "lon") val lon: Double
)