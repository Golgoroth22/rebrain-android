package com.falin.valentin.foodapp.domain

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse

/**
 * This is a data class for work with [AccountFragment].
 *
 * @param data data of our object.
 * @param isLoading display request loading status.
 * @param error display error.
 *
 */
data class AccountUiResponse(
    val data: UserResponse? = null,
    val isLoading: Boolean,
    val error: Throwable? = null
)