package com.falin.valentin.foodapp.domain

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
    val location: LatLng,
    val name: String,
    val workingHours: String
)