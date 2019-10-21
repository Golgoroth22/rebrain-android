package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.utils.Generator

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class ProductListViewModel(
    private val repository: ProductsRepository
) : ViewModel() {

    /**
     * This method can be called for get [List] of pictures Id`s.
     *
     */
    fun getPictures(): List<Int> {
        return repository.getPictures()
    }

    /**
     * This method can be called for get [List] of [Any] products.
     *
     */
    fun getProducts(): List<Any> {
        return repository.getProducts()
    }
}