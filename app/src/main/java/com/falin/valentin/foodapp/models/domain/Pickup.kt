package com.falin.valentin.foodapp.models.domain

/**
 * POJO class for display [Pickup].
 *
 * @property id Display object id
 * @property location Display object coordinates
 * @property name Display object name
 * @property workingHours Display opening hours
 */
data class Pickup(
    val id: Int,
    val location: Location,
    val name: String,
    val workingHours: String
)