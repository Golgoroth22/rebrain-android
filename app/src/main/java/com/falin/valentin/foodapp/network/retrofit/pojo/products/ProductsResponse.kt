package com.falin.valentin.foodapp.network.retrofit.pojo.products

import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [ProductsResponse].
 *
 * @property data Display list of [ProductResponse]
 * @property banners Display list of [BannerResponse]
 */
data class ProductsResponse(
    @Json(name = "data") val data: List<ProductResponse>,
    @Json(name = "banners") val banners: List<BannerResponse>
)