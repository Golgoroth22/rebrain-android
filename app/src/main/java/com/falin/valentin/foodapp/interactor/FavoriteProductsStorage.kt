package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.domain.Product
import javax.inject.Inject

/**
 * Interactor-layer class for work with favorite products.
 *
 * @property list List for containing favorite products
 */
class FavoriteProductsStorage @Inject constructor() {
    private var list = mutableListOf<Product>()

    /**
     * This method can be called for add new product in [list].
     *
     * @param product Product for adding
     */
    fun addProduct(product: Product) {
        if (!list.contains(product)) {
            list.add(product)
        }
    }

    /**
     * This method can be called for remove product in [list].
     *
     * @param product Product for removing
     */
    fun removeProduct(product: Product) {
        list.remove(product)
    }

    /**
     * This method can be called for get current list of favorite products.
     *
     */
    fun getData() = list
}