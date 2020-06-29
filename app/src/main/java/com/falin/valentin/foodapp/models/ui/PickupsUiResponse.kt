package com.falin.valentin.foodapp.models.ui

import com.falin.valentin.foodapp.models.domain.Pickup

/**
 * This is a data class for work with [MapActivity].
 *
 * @param data data of our object.
 * @param isLoading display request loading status.
 * @param error display error.
 *
 */
data class PickupsUiResponse(
    val data: List<Pickup>? = null,
    val isLoading: Boolean,
    val error: Throwable? = null
)

