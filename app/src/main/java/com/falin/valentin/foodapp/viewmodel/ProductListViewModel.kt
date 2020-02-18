package com.falin.valentin.foodapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import okhttp3.*
import timber.log.Timber
import java.io.IOException

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property products Property for contain products. Wrapped in [MutableLiveData]
 */
class ProductListViewModel(
    private val productsRepository: ProductsRepository,
    private val productsDisplayDisplayModeRepository: ProductsDisplayModeRepository,
    private val okHttpClient: OkHttpClient
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

    fun sendSomeRequest() {
        val request = Request.Builder()
            .url("http://api.android.srwx.net/api/v2")
            .build()
        RebrainApp.DAGGER.context()
        try {
            return okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Timber.e("Timber228 $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    Timber.i("Timber228 ${response.body}")
                }
            })
        } catch (e: IOException) {
            Timber.e(e)
        }
    }

    init {
        products.postValue(productsRepository.getProducts())
    }
}