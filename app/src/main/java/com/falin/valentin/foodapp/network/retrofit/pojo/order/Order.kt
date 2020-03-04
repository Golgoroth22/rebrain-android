package com.falin.valentin.foodapp.network.retrofit.pojo.order

import com.falin.valentin.foodapp.network.retrofit.pojo.products.Product
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [Order].
 *
 * @property data Display list of [Product]
 */
data class Order(
    @SerializedName("data")
    @Expose
    val data: List<Product>
)