package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.repository.AccountFragmentRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AccountFragmentViewModel(private val repository: AccountFragmentRepository) : ViewModel() {
    /**
     * This method can be called for get user email.
     *
     * @return User email
     */
    fun getUserEmail() = repository.getUserEmail()
}