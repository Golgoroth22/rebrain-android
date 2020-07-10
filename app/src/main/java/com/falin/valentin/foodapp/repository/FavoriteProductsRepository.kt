package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.models.domain.Product
import com.falin.valentin.foodapp.interactor.FavoriteProductsStorage
import com.falin.valentin.foodapp.interactor.Storage
import com.falin.valentin.foodapp.interactor.StorageJob

/**
 * Repository-layer class for work with products data.
 *
 */
class FavoriteProductsRepository(
    private val storage: Storage<List<Product>>,
    private val storageJob: StorageJob
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
        storageJob.addProduct(product)
    }
}