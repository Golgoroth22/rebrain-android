package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.models.domain.Product
import com.falin.valentin.foodapp.repository.FavoriteProductsRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property products Property for contain products. Wrapped in [MutableLiveData]
 */
class FavoriteProductListViewModel(private val productsRepository: FavoriteProductsRepository) :
    ViewModel() {
    private val mProducts: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = mProducts

    init {
        mProducts.postValue(productsRepository.getProducts())
    }
}