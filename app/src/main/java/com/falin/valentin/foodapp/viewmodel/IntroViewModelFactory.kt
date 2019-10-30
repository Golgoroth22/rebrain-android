package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.IntroInfoRepository

/**
 * [ViewModelProvider.Factory] subclass for creation of [IntroViewModel] class.
 *
 */
@Suppress("UNCHECKED_CAST")
class IntroViewModelFactory(private val introRepository: IntroInfoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IntroViewModel(introRepository) as T
    }
}