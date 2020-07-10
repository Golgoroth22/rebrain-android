package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.models.domain.Product
import com.falin.valentin.foodapp.interactor.StorageJob
import com.falin.valentin.foodapp.network.retrofit.pojo.products.ProductsResponse
import com.falin.valentin.foodapp.network.retrofit.service.ProductsService
import com.falin.valentin.foodapp.network.retrofit.service.RetrofitCallback
import com.falin.valentin.foodapp.utils.Generator

/**
 * Repository-layer class for work with products data.
 *
 */
class ProductsRepository(
    private val generator: Generator,
    private val favoriteProductsJob: StorageJob,
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
    fun sendProductsRequest(
        onSuccess: (ProductsResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        productsService.getProducts("", false)
            .enqueue(RetrofitCallback<ProductsResponse>(onSuccess, onFailure))
    }

    /**
     * This method can be called for add product to favorites.
     *
     */
    fun addProductToFavorites(product: Product) {
        favoriteProductsJob.addProduct(product)
    }
}