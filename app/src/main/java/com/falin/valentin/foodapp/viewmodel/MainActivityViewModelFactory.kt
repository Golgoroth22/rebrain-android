package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * [ViewModelProvider.Factory] subclass for creation of [MainActivityViewModel] class.
 *
 */
class MainActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel() as T
    }
}