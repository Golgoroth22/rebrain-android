package com.falin.valentin.foodapp.network.retrofit.pojo.products

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [Products].
 *
 * @property data Display list of [Product]
 * @property banners Display list of [Banner]
 */
data class Products(
    @SerializedName("data")
    @Expose
    val data: List<Product>,
    @SerializedName("banners")
    @Expose
    val banners: List<Banner>
)