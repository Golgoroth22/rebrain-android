package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.domain.Product
import com.falin.valentin.foodapp.network.retrofit.pojo.products.ProductsResponse
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
        products.enqueue(object : Callback<ProductsResponse> {
            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Timber.e("ProductsRepository sendProductsRequest onFailure ${t.message}")
            }

            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                Timber.i("ProductsRepository sendProductsRequest onResponse ${response.body()?.data?.size}")
            }
        })
    }
}