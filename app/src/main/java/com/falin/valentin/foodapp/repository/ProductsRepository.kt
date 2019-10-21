package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.utils.Generator

/**
 * Repository-layer class for work with products data.
 *
 */
class ProductsRepository(
    private val generator: Generator
) {

    /**
     * This method can be called for get [List] of [Any] products.
     *
     */
    fun getProducts(): List<Any> {
        return generator.getProducts() as List<Any>
    }


    /**
     * This method can be called for get [List] of pictures.
     *
     */
    fun getPictures(): List<Int> {
        return generator.picturesList
    }
}