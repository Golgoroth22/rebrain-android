package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.AuthorizationStatusRepository
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [AccountTabFragmentViewModel] class.
 *
 */
class AccountTabFragmentViewModelFactory @Inject constructor(private val tabRepository: AuthorizationStatusRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountTabFragmentViewModel(tabRepository) as T
    }
}