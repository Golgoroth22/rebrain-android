package com.falin.valentin.foodapp.network.retrofit.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit interface for logout.
 *
 */
interface LogoutService {
    @GET("logout")
    fun logout(@Header("Authorization") token: String): Call<String>
}