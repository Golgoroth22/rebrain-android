package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.IntroDisplayStorage

/**
 * Repository-layer class for work product mode displaying.
 *
 */
class IntroInfoRepository(
    private val introStorage: IntroDisplayStorage
) {
    /**
     * This method can be called for get current intro status.
     *
     */
    fun isIntroShowed() = introStorage.isIntroShowed()

    /**
     * This method can be called for set intro activity status on Showed.
     *
     */
    fun setIntroShowed() {
        introStorage.setIntroShowed()
    }
}