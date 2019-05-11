package com.falin.valentin.foodapp.utils

import com.falin.valentin.foodapp.domain.Product

class Generator {
    var productList: MutableList<Product> = mutableListOf()

    init {
        for (i in 1..20) {
            productList.add(Product(i, "Product №$i"))
        }
    }

    fun getProducts(): List<Product> {
        return productList.shuffled()
    }
}