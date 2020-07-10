package com.falin.valentin.foodapp.network.retrofit.pojo.products

import com.falin.valentin.foodapp.models.domain.Product
import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [ProductResponse].
 *
 * @property id Display object id
 * @property image Display object image link
 * @property name Display product price
 * @property price Display product price
 * @property isFavorite Display is this product in favorites or not
 */
data class ProductResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "image") val image: String,
    @Json(name = "name") val name: String,
    @Json(name = "price") val price: Double,
    @Json(name = "isFavorite") val isFavorite: Boolean
) {
    fun convertTo() = Product(
        id,
        name,
        price.toInt(),
        image
    )
}