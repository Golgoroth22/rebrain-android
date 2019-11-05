package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.ProductModeStorageImpl

/**
 * Repository-layer class for work product mode displaying.
 *
 */
class ProductsDisplayModeRepository(private val displayMode: ProductModeStorageImpl) {
    /**
     * This method can be called for get current products display mode.
     *
     */
    fun getDisplayMode() = displayMode.getDisplayMode()

    /**
     * This method can be called for switch and save current products display mode.
     *
     */
    fun switchDisplayMode() {
        displayMode.switchDisplayMode()
    }
}