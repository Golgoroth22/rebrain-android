package com.falin.valentin.foodapp.network.retrofit.pojo.products

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [Banner].
 *
 * @property id Display object id
 * @property image Display object image link
 */
data class Banner(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("image")
    @Expose
    val image: String
)