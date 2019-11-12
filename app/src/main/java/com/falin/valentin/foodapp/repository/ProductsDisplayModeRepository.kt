package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.Storage

/**
 * Repository-layer class for work product mode displaying.
 *
 */
class ProductsDisplayModeRepository(private val displayMode: Storage<Int>) {
    /**
     * This method can be called for get current products display mode.
     *
     */
    fun getDisplayMode() = displayMode.getData()

    /**
     * This method can be called for switch and save current products display mode.
     *
     */
    fun switchDisplayMode() {
        displayMode.setData()
    }
}