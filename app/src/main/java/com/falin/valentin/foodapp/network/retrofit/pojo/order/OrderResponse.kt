package com.falin.valentin.foodapp.network.retrofit.pojo.order

import com.falin.valentin.foodapp.network.retrofit.pojo.products.ProductResponse
import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [OrderResponse].
 *
 * @property data Display list of [ProductResponse]
 */
data class OrderResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "products") val products: List<ProductResponse>,
    @Json(name = "productsCount") val productsCount: Int,
    @Json(name = "price") val price: Double
)