package com.falin.valentin.foodapp.network.retrofit.pojo.pickups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [LatLng].
 *
 * @property lat Display latitude
 * @property lon Display longitude
 */
data class LatLng(
    @SerializedName("lat")
    @Expose
    val lat: Double,
    @SerializedName("lon")
    @Expose
    val lon: Double
)