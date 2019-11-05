package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.utils.PreferencesHelper

/**
 * Interactor-layer class for work with intro.
 *
 */
class IntroDisplayStorage(
    private val preferences: PreferencesHelper
) {
    /**
     * This method can be called for get current intro status.
     *
     */
    fun isIntroShowed() = preferences.introInfo

    /**
     * This method can be called for set intro activity status on Showed.
     *
     */
    fun setIntroShowed() {
        preferences.introInfo = true
    }
}