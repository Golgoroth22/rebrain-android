package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.utils.Generator
import okhttp3.*
import timber.log.Timber
import java.io.IOException

/**
 * Repository-layer class for work with products data.
 *
 */
class ProductsRepository(
    private val generator: Generator,
    private var okHttpClient: OkHttpClient
) {

    /**
     * This method can be called for get [List] of [Any] products.
     *
     */
    fun getProducts(): List<Product> {
        return generator.getProducts() as List<Product>
    }


    /**
     * This method can be called for get [List] of pictures.
     *
     */
    fun getPictures(): List<Int> {
        return generator.picturesList
    }

    /**
     * This method can be called for send some request.
     *
     */
    fun sendSomeRequest() {
        val request = Request.Builder()
            .url("http://api.android.srwx.net/api/v2")
            .build()
        try {
            okHttpClient.newCall(request).enqueue(object : Callback {
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
}