package com.falin.valentin.foodapp.network.retrofit.pojo.order

import com.falin.valentin.foodapp.network.retrofit.pojo.products.ProductResponse
import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [OrderDataResponse].
 *
 * @property data Display list of [ProductResponse]
 */
data class OrderDataResponse(
    @Json(name = "data") val data: List<ProductResponse>
)