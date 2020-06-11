package com.falin.valentin.foodapp.domain

/**
 * This is a data class for work with [AuthorizationFragment].
 *
 * @param data data of our object.
 * @param isLoading display request loading status.
 * @param error display error.
 *
 */
data class UserUiResponse(
    val data: User? = null,
    val isLoading: Boolean,
    val error: Throwable? = null
)

