package com.falin.valentin.foodapp.domain

/**
 * This is a data class.
 *
 * @param id Is a ID of our object.
 * @param name Is a name of our object.
 * @param price Is a price of our object.
 *
 */
data class Product(
    val id: Int,
    val name: String,
    val price: Int
)