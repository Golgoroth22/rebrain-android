package com.falin.valentin.foodapp.utils

import com.falin.valentin.foodapp.domain.Product


/**
 *  Generator class.
 *
 */
class Generator {
    private val list: List<*> by lazy {
        initProductList()
    }

    private fun initProductList(): List<Any> {
        return (1..20).map {
            Product(1, "Product №$it", it * 10 * (it + 3), "https://tinyurl.com/yyvx5cvp")
        }
    }

    fun getProducts(): List<Any?> {
        return list.shuffled()
    }
}