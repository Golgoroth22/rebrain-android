package com.falin.valentin.foodapp.network.retrofit.pojo.login

import com.squareup.moshi.Json

/**
 * POJO class for display retrofit [LoginRequest].
 *
 * @property login Display user email
 * @property password Display user password
 */
data class LoginRequest(
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String
)