package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

/**
 * Retrofit interface for put new user avatar.
 *
 */
interface UserAvatarService {
    @Multipart
    @PUT("user/avatar/")
    fun putAvatar(
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<UserResponse>
}