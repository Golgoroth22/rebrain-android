package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.utils.Generator

/**
 * Repository-layer class for work with products data.
 *
 */
class ProductsRepository {
    val picturesList: List<Int> = listOf(
        R.drawable.food_1,
        R.drawable.food_2,
        R.drawable.food_3,
        R.drawable.food_4,
        R.drawable.food_5,
        R.drawable.food_6,
        R.drawable.food_7,
        R.drawable.food_8,
        R.drawable.food_9,
        R.drawable.food_10
    )

    /**
     * This method can be called for get [List] of [Any] products.
     *
     */
    fun getProducts(): List<Any> {
        return Generator().getProducts() as List<Any>
    }
}