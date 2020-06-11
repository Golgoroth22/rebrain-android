package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit interface for user.
 *
 */
interface UserService {
    @GET("user/")
    fun getUser(@Header("x-access-token") token: String): Call<UserResponse>
}