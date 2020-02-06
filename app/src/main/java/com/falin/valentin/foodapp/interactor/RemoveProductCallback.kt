package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.domain.Product

/**
 * Interface for remove product callback.
 *
 */
interface RemoveProductCallback {
    /**
     * This method can be called for remove product.
     *
     * @param product Product for removing
     */
    fun removeProduct(product: Product)
}