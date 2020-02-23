package com.falin.valentin.foodapp.network.retrofit.pojo.pickups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [Pickup].
 *
 * @property id Display object id
 * @property location Display object coordinates
 * @property name Display object name
 * @property workingHours Display opening hours
 */
data class Pickup(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("location")
    @Expose
    val location: LatLng,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("workingHours")
    @Expose
    val workingHours: String
)