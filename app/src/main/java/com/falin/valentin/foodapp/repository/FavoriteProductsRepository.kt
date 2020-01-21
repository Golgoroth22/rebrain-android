package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.utils.Generator

/**
 * Repository-layer class for work with products data.
 *
 */
class FavoriteProductsRepository(
    private val generator: Generator
) {
    /**
     * This method can be called for get [List] of [Any] products.
     *
     */
    fun getProducts(): List<Product> {
        return generator.getProducts() as List<Product>
    }
}