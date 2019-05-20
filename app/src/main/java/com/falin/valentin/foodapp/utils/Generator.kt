package com.falin.valentin.foodapp.utils

import com.falin.valentin.foodapp.domain.Product


/**
 *  Class generator of [Any] objects.
 *
 */
class Generator {
    private val list: List<*> by lazy {
        initProductList()
    }

    private fun initProductList(): List<Any> {
        return (1..20).map {
            Product(1, "Product №$it")
        }
    }

    fun getProducts(): List<Any?> {
        return list.shuffled()
    }
}