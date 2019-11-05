package com.falin.valentin.foodapp.interactor

/**
 * Interface for work with [ProductModeStorage].
 *
 */
interface ProductModeStorageImpl {
    /**
     * This method can be called for get current products display mode.
     *
     */
    fun getDisplayMode(): Int

    /**
     * This method can be called for switch and save current products display mode.
     *
     */
    fun switchDisplayMode()
}