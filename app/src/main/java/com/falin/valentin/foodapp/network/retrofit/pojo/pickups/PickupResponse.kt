package com.falin.valentin.foodapp.network.retrofit.pojo.pickups

import com.falin.valentin.foodapp.domain.LatLng
import com.falin.valentin.foodapp.domain.Pickup
import com.falin.valentin.foodapp.network.retrofit.pojo.BaseResponse
import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [PickupResponse].
 *
 * @property id Display object id
 * @property location Display object coordinates
 * @property name Display object name
 * @property workingHours Display opening hours
 */
data class PickupResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "location") val location: LatLngResponse,
    @Json(name = "name") val name: String,
    @Json(name = "workingHours") val workingHours: String
) : BaseResponse<Pickup> {

    override fun convert() = Pickup(id, LatLng(location.lat, location.lon), name, workingHours)
}