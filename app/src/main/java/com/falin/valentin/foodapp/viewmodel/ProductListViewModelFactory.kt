package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [ProductListViewModel] class.
 *
 */
@Suppress("UNCHECKED_CAST")
class ProductListViewModelFactory @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val productsDisplayDisplayModeRepository: ProductsDisplayModeRepository,
    private val okHttpClient: OkHttpClient
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(productsRepository, productsDisplayDisplayModeRepository, okHttpClient) as T
    }
}