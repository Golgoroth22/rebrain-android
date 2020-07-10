package com.falin.valentin.foodapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.IntroInfoRepository
import com.falin.valentin.foodapp.viewmodel.IntroViewModel
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [IntroViewModel] class.
 *
 */
@Suppress("UNCHECKED_CAST")
class IntroViewModelFactory @Inject constructor(private val introRepository: IntroInfoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IntroViewModel(
            introRepository
        ) as T
    }
}