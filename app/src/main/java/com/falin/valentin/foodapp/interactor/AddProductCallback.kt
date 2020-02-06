package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.domain.Product

/**
 * Interface for add product callback.
 *
 */
interface AddProductCallback {
    /**
     * This method can be called for add new product.
     *
     * @param product Product for adding
     */
    fun addProduct(product: Product)
}