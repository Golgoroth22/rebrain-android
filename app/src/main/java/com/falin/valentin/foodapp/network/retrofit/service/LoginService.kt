package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Retrofit interface for login.
 *
 */
interface LoginService {
    @FormUrlEncoded
    @POST("login/")
    fun login(
        @Header("Content-Type") contentType: String = "application/json",
        @Field("login") login: String,
        @Field("password") password: String
    ): Call<UserResponse>
}