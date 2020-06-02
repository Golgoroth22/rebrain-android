package com.falin.valentin.foodapp.network.retrofit.pojo.login

import com.falin.valentin.foodapp.domain.User
import com.falin.valentin.foodapp.network.retrofit.pojo.BaseResponse
import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [UserResponse].
 *
 * @property id Display object id
 * @property name Display object name
 * @property login Display something
 * @property avatar Display avatar url
 * @property token Display user token
 */
data class UserResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "login") val login: String,
    @Json(name = "avatar") val avatar: String?,
    @Json(name = "accessToken") val token: String?
) : BaseResponse<User> {

    override fun convert() = User(id, name, login, avatar, token)
}