package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.login.LoginRequest
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Retrofit interface for login.
 *
 */
interface LoginService {
    @POST("login/")
    fun login(@Body loginRequest: LoginRequest): Call<UserResponse>
}