package com.falin.valentin.foodapp.domain

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse

data class LoginUiResponse(
    val data: UserResponse? = null,
    val message: String
)