package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.products.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Retrofit interface for get products.
 *
 */
interface ProductsService {
    @GET("products")
    fun getProducts(
        @Header("Authorization") token: String,
        @Query("is_favourite") isFavourite: Boolean
    ): Call<Products>
}