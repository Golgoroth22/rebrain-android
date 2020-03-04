package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.login.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

/**
 * Retrofit interface for put new user avatar.
 *
 */
interface UserAvatarService {
    @PUT("user/avatar")
    fun putAvatar(@Body avatar: String): Call<User>
}