package com.falin.valentin.foodapp.network.retrofit.pojo.products

import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [BannerResponse].
 *
 * @property id Display object id
 * @property image Display object image link
 */
data class BannerResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "image") val image: String
)