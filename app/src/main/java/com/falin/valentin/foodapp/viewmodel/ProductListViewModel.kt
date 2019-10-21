package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.utils.Generator

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class ProductListViewModel(
    private val repository: ProductsRepository
) : ViewModel() {

    var products: MutableLiveData<List<Product>> = MutableLiveData()

    /**
     * This method can be called for get [List] of pictures Id`s.
     *
     */
    fun getPictures(): List<Int> {
        return repository.getPictures()
    }

    init {
        products.postValue(repository.getProducts())
    }
}