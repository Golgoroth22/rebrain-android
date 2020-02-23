package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.login.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit interface for user.
 *
 */
interface UserService {
    @GET("user")
    fun getUser(@Header("Authorization") token: String): Call<User>
}