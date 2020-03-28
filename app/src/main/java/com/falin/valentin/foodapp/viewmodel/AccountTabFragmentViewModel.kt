package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.repository.AccountTabRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AccountTabFragmentViewModel(private val tabRepository: AccountTabRepository) : ViewModel() {

    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized() = tabRepository.isUserAuthorized()
}