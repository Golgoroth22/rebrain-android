package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * [ViewModelProvider.Factory] subclass for creation of [ProductListViewModel] class.
 *
 */
@Suppress("UNCHECKED_CAST")
class ProductListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel() as T
    }
}