package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.repository.AccountTabRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AccountTabFragmentViewModel(private val tabRepository: AccountTabRepository) : ViewModel() {
    fun isUserAuthorized() = tabRepository.isUserAuthorized()
}