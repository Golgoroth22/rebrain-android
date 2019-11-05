package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class ProductListViewModel(
    private val productsRepository: ProductsRepository,
    private val productsDisplayDisplayModeRepository: ProductsDisplayModeRepository
) : ViewModel() {

    var products: MutableLiveData<List<Product>> = MutableLiveData()

    /**
     * This method can be called for get [List] of pictures Id`s.
     *
     */
    fun getPictures(): List<Int> {
        return productsRepository.getPictures()
    }

    fun getProductsDisplayMode() = productsDisplayDisplayModeRepository.getDisplayMode()

    fun switchDisplayMode() {
        productsDisplayDisplayModeRepository.switchDisplayMode()
    }

    init {
        products.postValue(productsRepository.getProducts())
    }
}