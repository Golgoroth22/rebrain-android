package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.utils.Generator

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class ProductListViewModel : ViewModel() {
    var picturesList: List<Int> = listOf(
        R.drawable.food_1,
        R.drawable.food_2,
        R.drawable.food_3,
        R.drawable.food_4,
        R.drawable.food_5,
        R.drawable.food_6,
        R.drawable.food_7,
        R.drawable.food_8,
        R.drawable.food_9,
        R.drawable.food_10
    )
        private set

    fun getProducts(): List<Any> {
        return Generator().getProducts() as List<Any>
    }
}