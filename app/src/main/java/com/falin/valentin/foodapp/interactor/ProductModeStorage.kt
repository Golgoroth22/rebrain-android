package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.screen.main.adapter.MainTabElementAdapter
import com.falin.valentin.foodapp.utils.PreferencesHelper

/**
 * Interactor-layer class for work with product display mode.
 *
 */
class ProductModeStorage(
    private val preferences: PreferencesHelper
) {
    /**
     * This method can be called for get current products display mode.
     *
     */
    fun getDisplayMode() = preferences.productMode

    /**
     * This method can be called for switch and save current products display mode.
     *
     */
    fun switchDisplayMode() {
        when (preferences.productMode) {
            MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR.ordinal -> preferences.productMode =
                MainTabElementAdapter.LayoutManagerDisplayMode.GRID.ordinal
            MainTabElementAdapter.LayoutManagerDisplayMode.GRID.ordinal -> preferences.productMode =
                MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR.ordinal
        }
    }
}