package com.falin.valentin.foodapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.AuthorizationRepository
import com.falin.valentin.foodapp.viewmodel.AuthorizationFragmentViewModel
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [AuthorizationFragmentViewModel] class.
 *
 */
class AuthorizationFragmentViewModelFactory @Inject constructor(
    private val repository: AuthorizationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthorizationFragmentViewModel(
            repository
        ) as T
    }
}