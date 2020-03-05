package com.falin.valentin.foodapp.network.retrofit.pojo.login

import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [UserResponse].
 *
 * @property id Display object id
 * @property name Display object name
 * @property login Display something
 * @property avatar Display avatar url
 */
data class UserResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "login") val login: String,
    @Json(name = "avatar") val avatar: String
)