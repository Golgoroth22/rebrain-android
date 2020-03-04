package com.falin.valentin.foodapp.network.retrofit.pojo.products

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [Product].
 *
 * @property id Display object id
 * @property image Display object image link
 * @property name Display product price
 * @property price Display product price
 * @property isFavorite Display is this product in favorites or not
 */
data class Product(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("price")
    @Expose
    val price: Double,
    @SerializedName("isFavorite")
    @Expose
    val isFavorite: Boolean
)