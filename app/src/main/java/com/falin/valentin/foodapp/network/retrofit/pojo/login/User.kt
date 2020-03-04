package com.falin.valentin.foodapp.network.retrofit.pojo.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO class for display retrofit [User].
 *
 * @property id Display object id
 * @property name Display object name
 * @property login Display something
 * @property avatar Display avatar url
 */
data class User(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("login")
    @Expose
    val login: String,
    @SerializedName("avatar")
    @Expose
    val avatar: String
)