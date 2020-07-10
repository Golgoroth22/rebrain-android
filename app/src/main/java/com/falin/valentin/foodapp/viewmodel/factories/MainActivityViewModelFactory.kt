package com.falin.valentin.foodapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.viewmodel.MainActivityViewModel

/**
 * [ViewModelProvider.Factory] subclass for creation of [MainActivityViewModel] class.
 *
 */
class MainActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel() as T
    }
}