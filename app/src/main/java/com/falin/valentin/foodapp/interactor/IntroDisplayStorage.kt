package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.utils.PreferencesHelper

/**
 * Interactor-layer class for work with intro.
 *
 */
class IntroDisplayStorage(
    private val preferences: PreferencesHelper
) : IntroDisplayStorageImpl {
    /**
     * This method can be called for get current intro status.
     *
     */
    override fun isIntroShowed() = preferences.introInfo

    /**
     * This method can be called for set intro activity status on Showed.
     *
     */
    override fun setIntroShowed() {
        preferences.introInfo = true
    }
}