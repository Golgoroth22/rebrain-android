package com.falin.valentin.foodapp.network.retrofit.service

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Header
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
    fun setAvatar(
        @Header("x-access-token") token: String,
        @Part file: MultipartBody.Part
    ): Call<Unit>
}