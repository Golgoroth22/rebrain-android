package com.falin.valentin.foodapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.FirebaseCloudStorageRepository
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.viewmodel.ProductListViewModel
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [ProductListViewModel] class.
 *
 */
@Suppress("UNCHECKED_CAST")
class ProductListViewModelFactory @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val imagesRepository: FirebaseCloudStorageRepository,
    private val productsDisplayDisplayModeRepository: ProductsDisplayModeRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(
            productsRepository,
            imagesRepository,
            productsDisplayDisplayModeRepository
        ) as T
    }
}