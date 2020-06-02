package com.falin.valentin.foodapp.domain


/**
 * POJO class for display user entity.
 *
 * @property id Display user id
 * @property name Display user name
 * @property login Display user login
 * @property avatar Display user avatar url
 * @property token Display user token
 */
data class User(
    val id: Int,
    val name: String,
    val login: String,
    val avatar: String?,
    val token: String?
)