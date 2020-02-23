package com.falin.valentin.foodapp.network.retrofit.pojo.order

import com.falin.valentin.foodapp.network.retrofit.pojo.products.Product
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [OrderResponse].
 *
 * @property data Display list of [Product]
 */
data class OrderResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("products")
    @Expose
    val products: List<Product>,
    @SerializedName("productsCount")
    @Expose
    val productsCount: Int,
    @SerializedName("price")
    @Expose
    val price: Double
)