package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.domain.Product
import javax.inject.Inject

/**
 * Interactor-layer class for work with favorite products.
 *
 * @property list List for containing favorite products
 */
class FavoriteProductsStorage @Inject constructor(): AddProductCallback, RemoveProductCallback {
    private var list = mutableListOf<Product>()

    override fun addProduct(product: Product) {
        if (!list.contains(product)) {
            list.add(product)
        }
    }

    override fun removeProduct(product: Product) {
        list.remove(product)
    }

    /**
     * This method can be called for get current list of favorite products.
     *
     */
    fun getData() = list
}