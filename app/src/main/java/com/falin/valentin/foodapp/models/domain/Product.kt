package com.falin.valentin.foodapp.models.domain

/**
 * This is a data class.
 *
 * @param id  ID of our object.
 * @param name  Name of our object.
 * @param price  Price of our object.
 * @param imageUrl  Image of our object.
 *
 */
data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val imageUrl: String
)