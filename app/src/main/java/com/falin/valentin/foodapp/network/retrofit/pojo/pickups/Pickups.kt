package com.falin.valentin.foodapp.network.retrofit.pojo.pickups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [Pickups].
 *
 * @property data Display list of [Pickup]
 */
data class Pickups(
    @SerializedName("data")
    @Expose
    val data: List<Pickup>
)