package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.network.retrofit.pojo.products.Products
import com.falin.valentin.foodapp.network.retrofit.service.ProductsService
import com.falin.valentin.foodapp.utils.Generator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Repository-layer class for work with products data.
 *
 */
class ProductsRepository(
    private val generator: Generator,
    private val productsService: ProductsService
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
    fun sendProductsRequest() {
        val products = productsService.getProducts("", false)
        products.enqueue(object : Callback<Products> {
            override fun onFailure(call: Call<Products>, t: Throwable) {
                Timber.e("Timber228 onFailure ${t.message}")
            }

            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                Timber.i("Timber228 onResponse ${response.body()?.data?.size}")
            }
        })
    }
}