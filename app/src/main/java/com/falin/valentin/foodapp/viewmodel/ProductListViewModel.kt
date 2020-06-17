package com.falin.valentin.foodapp.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.network.retrofit.pojo.products.ProductsResponse
import com.falin.valentin.foodapp.repository.FirebaseCloudStorageRepository
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property productsLiveData Property for contain products. Wrapped in [MutableLiveData]
 * @property productsList Contain last list of products
 * @property picturesLiveData Contain list of pictures wrapped in [LiveData]
 * @property picturesList Contain last list of pictures
 */
class ProductListViewModel(
    private val productsRepository: ProductsRepository,
    private val picturesRepository: FirebaseCloudStorageRepository,
    private val productsDisplayDisplayModeRepository: ProductsDisplayModeRepository
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
    private val mProductsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>> = mProductsLiveData
    var productsList = emptyList<Product>()
    private val mPicturesLiveData = MutableLiveData<List<Uri>>()
    val picturesLiveData = mPicturesLiveData
    var picturesList = emptyList<Uri>()

    /**
     * This method can be called for get [List] of pictures Id`s.
     *
     */
    private fun getPictures() = launch {
        mPicturesLiveData.postValue(picturesRepository.getUris())
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
     * This method can be called for add product to favorites.
     *
     */
    fun addProductToFavorites(product: Product) {
        productsRepository.addProductToFavorites(product)
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
        mProductsLiveData.postValue(productsRepository.getProducts())
        getPictures()
    }
}