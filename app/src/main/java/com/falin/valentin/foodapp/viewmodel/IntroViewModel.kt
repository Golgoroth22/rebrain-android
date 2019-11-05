package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.repository.IntroInfoRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class IntroViewModel(
    private val repository: IntroInfoRepository
) : ViewModel() {
    /**
     * This method can be called for get current intro status.
     *
     */
    fun isIntroShowed() = repository.isIntroShowed()

    /**
     * This method can be called for set intro activity status on Showed.
     *
     */
    fun setIntroShowed() {
        repository.setIntroShowed()
    }
}