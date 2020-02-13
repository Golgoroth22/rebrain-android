package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.interactor.FavoriteProductsStorage

/**
 * Repository-layer class for work with products data.
 *
 */
class FavoriteProductsRepository(
    private val storage: FavoriteProductsStorage
) {
    /**
     * This method can be called for get [List] of [Any] products.
     *
     */
    fun getProducts() = storage.getData()

    /**
     * This method can be called for update [List].
     *
     */
    fun setProducts(product: Product) {
        storage.addProduct(product)
    }
}