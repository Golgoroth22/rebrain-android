package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.utils.PreferencesHelper
import javax.inject.Inject

/**
 * Interactor-layer class for work with intro.
 *
 */
class IntroDisplayStorage @Inject constructor(
    private val preferences: PreferencesHelper
) : Storage<Boolean> {
    /**
     * This method can be called for get current intro status.
     *
     */
    override fun getData() = preferences.introInfo

    /**
     * This method can be called for set intro activity status on Showed.
     *
     */
    override fun setData() {
        preferences.introInfo = true
    }
}