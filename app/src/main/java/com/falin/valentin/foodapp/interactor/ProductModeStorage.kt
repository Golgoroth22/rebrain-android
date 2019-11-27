package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.screen.main.adapter.MainTabElementAdapter
import com.falin.valentin.foodapp.utils.PreferencesHelper
import javax.inject.Inject

/**
 * Interactor-layer class for work with product display mode.
 *
 */
class ProductModeStorage @Inject constructor(
    private val preferences: PreferencesHelper
) : Storage<Int> {
    /**
     * This method can be called for get current products display mode.
     *
     */
    override fun getData() = preferences.productDisplayMode

    /**
     * This method can be called for switch and save current products display mode.
     *
     */
    override fun setData() {
        when (preferences.productDisplayMode) {
            MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR.ordinal -> preferences.productDisplayMode =
                MainTabElementAdapter.LayoutManagerDisplayMode.GRID.ordinal
            MainTabElementAdapter.LayoutManagerDisplayMode.GRID.ordinal -> preferences.productDisplayMode =
                MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR.ordinal
        }
    }
}