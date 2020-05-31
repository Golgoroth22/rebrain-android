package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.network.retrofit.pojo.products.ProductsResponse
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import timber.log.Timber

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property products Property for contain products. Wrapped in [MutableLiveData]
 */
class ProductListViewModel(
    private val productsRepository: ProductsRepository,
    private val productsDisplayDisplayModeRepository: ProductsDisplayModeRepository
) : ViewModel() {
    private val mProducts = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = mProducts

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
     * This method can be called for send products request.
     *
     */
    fun getProducts() {
        productsRepository.sendProductsRequest(
            { response -> receiveSuccessfulResponse(response) },
            { throwable -> receiveFailureResponse(throwable) }
        )
    }

    private fun receiveSuccessfulResponse(productsResponse: ProductsResponse) {
        Timber.i("ProductListViewModel receiveSuccessfulResponse $productsResponse")
    }

    private fun receiveFailureResponse(t: Throwable) {
        Timber.e("ProductListViewModel receiveFailureResponse ${t.message}")
    }

    init {
        mProducts.postValue(productsRepository.getProducts())
    }
}