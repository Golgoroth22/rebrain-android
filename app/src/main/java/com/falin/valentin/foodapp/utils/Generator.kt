package com.falin.valentin.foodapp.utils

import com.falin.valentin.foodapp.domain.Product


/**
 *  Class generator of [Product] objects.
 *
 */
class Generator {
    private val list: List<Product> by lazy {
        initProductList()
    }

    private fun initProductList(): List<Product> {
        val list: MutableList<Product> = mutableListOf()
        for (i in 1..20) {
            list.add(Product(i, "Product №$i"))
        }
        return list
    }

    fun getProducts(): List<Product> {
        return list.shuffled()
    }
}