package com.falin.valentin.foodapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.AccountFragmentRepository
import com.falin.valentin.foodapp.viewmodel.AccountFragmentViewModel
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [AccountFragmentViewModel] class.
 *
 */
class AccountFragmentViewModelFactory @Inject constructor(private val repository: AccountFragmentRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountFragmentViewModel(
            repository
        ) as T
    }
}