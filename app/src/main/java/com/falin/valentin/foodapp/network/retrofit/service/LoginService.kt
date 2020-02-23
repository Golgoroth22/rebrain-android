package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.login.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

/**
 * Retrofit interface for login.
 *
 */
interface LoginService {
    @POST("login")
    fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): Call<User>
}