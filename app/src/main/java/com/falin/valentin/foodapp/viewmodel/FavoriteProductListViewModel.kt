package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.repository.FavoriteProductsRepository
import com.falin.valentin.foodapp.repository.ProductsRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property products Property for contain products. Wrapped in [MutableLiveData]
 */
class FavoriteProductListViewModel(private val productsRepository: FavoriteProductsRepository) :
    ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        products.postValue(productsRepository.getProducts())
    }
}