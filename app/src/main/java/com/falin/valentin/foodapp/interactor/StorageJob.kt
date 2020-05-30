package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.domain.Product

/**
 * Interface for work with data storage.
 *
 */
interface StorageJob {
    /**
     * This method can be called for add new product.
     *
     * @param product Product for adding
     */
    fun addProduct(product: Product)

    /**
     * This method can be called for remove product.
     *
     * @param product Product for removing
     */
    fun removeProduct(product: Product)
}