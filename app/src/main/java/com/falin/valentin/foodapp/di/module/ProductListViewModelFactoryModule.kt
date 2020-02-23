package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.network.retrofit.service.ProductsService
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.utils.Generator
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [ProductListViewModelFactory].
 *
 */
@Module
class ProductListViewModelFactoryModule {

    /**
     * This method can be called for get [ProductListViewModelFactory].
     *
     * @return [ProductListViewModelFactory]
     */
    @Provides
    @PerScreen
    fun provideFactory(storage: ProductModeStorage, productsService: ProductsService):
            ProductListViewModelFactory {
        return ProductListViewModelFactory(
            ProductsRepository(Generator(), productsService), ProductsDisplayModeRepository(storage))
    }
}