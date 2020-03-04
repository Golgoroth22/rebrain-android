package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.order.Order
import com.falin.valentin.foodapp.network.retrofit.pojo.order.OrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Retrofit interface for order.
 *
 */
interface OrderService {
    @POST("order")
    fun makeOrder(
        @Header("Authorization") token: String,
        @Body order: Order
    ): Call<OrderResponse>
}