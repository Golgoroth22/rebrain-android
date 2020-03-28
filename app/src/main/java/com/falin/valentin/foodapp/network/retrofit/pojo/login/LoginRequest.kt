package com.falin.valentin.foodapp.network.retrofit.pojo.login

/**
 * POJO class for display retrofit [LoginRequest].
 *
 * @property login Display user email
 * @property password Display user password
 */
data class LoginRequest(
    val login: String,
    val password: String
)