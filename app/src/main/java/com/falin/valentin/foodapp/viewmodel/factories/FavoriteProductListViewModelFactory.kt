package com.falin.valentin.foodapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.FavoriteProductsRepository
import com.falin.valentin.foodapp.viewmodel.FavoriteProductListViewModel
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [FavoriteProductListViewModel] class.
 *
 */
class FavoriteProductListViewModelFactory @Inject constructor(
    private val productsRepository: FavoriteProductsRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteProductListViewModel(
            productsRepository
        ) as T
    }
}