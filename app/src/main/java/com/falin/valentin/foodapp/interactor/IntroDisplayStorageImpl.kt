package com.falin.valentin.foodapp.interactor

/**
 * Interface for work with [IntroDisplayStorage].
 *
 */
interface IntroDisplayStorageImpl {
    /**
     * This method can be called for get current intro status.
     *
     */
    fun isIntroShowed() : Boolean

    /**
     * This method can be called for set intro activity status on Showed.
     *
     */
    fun setIntroShowed()
}