package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.domain.Product
import javax.inject.Inject

/**
 * Interactor-layer class for work with favorite products.
 *
 * @property list List for containing favorite products
 */
class FavoriteProductsStorage @Inject constructor() : Storage<List<Product>>, StorageJob {
    private var list = mutableListOf<Product>()

    override fun addProduct(product: Product) {
        if (!list.contains(product)) {
            list.add(product)
        }
    }

    override fun removeProduct(product: Product) {
        list.remove(product)
    }

    override fun getData() = list

    override fun setData() {
        list = mutableListOf()
    }
}