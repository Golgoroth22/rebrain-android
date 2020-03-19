package com.falin.valentin.foodapp.utils

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.domain.Product

/**
 *  Generator class.
 *
 */
class Generator {
    private val list: List<*> by lazy {
        initProductList()
    }

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

    private fun initProductList(): List<Any> {
        return (1..20).map {
            Product(1, "Product №$it", it * 10 * (it + 3), "https://tinyurl.com/yyvx5cvp")
        }
    }

    /**
     * This method can be called for get [List] of [Any] products.
     *
     */
    fun getProducts(): List<Any?> {
        return list.shuffled()
    }
}