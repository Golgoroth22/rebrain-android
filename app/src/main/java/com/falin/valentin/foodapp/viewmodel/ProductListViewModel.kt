package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property products Property for contain products. Wrapped in [MutableLiveData]
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

    /**
     * This method can be called for get current products display mode.
     *
     */
    fun getProductsDisplayMode() = productsDisplayDisplayModeRepository.getDisplayMode()

    /**
     * This method can be called for switch products display mode.
     *
     */
    fun switchDisplayMode() {
        productsDisplayDisplayModeRepository.switchDisplayMode()
    }

    /**
     * This method can be called for send some request.
     *
     */
    fun sendSomeRequest() {
        productsRepository.sendSomeRequest()
    }

    init {
        products.postValue(productsRepository.getProducts())
    }
}