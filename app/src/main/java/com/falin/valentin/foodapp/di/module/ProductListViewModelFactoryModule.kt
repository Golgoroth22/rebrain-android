package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.utils.Generator
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

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
    fun provideFactory(storage: ProductModeStorage, okHttpClient: OkHttpClient):
            ProductListViewModelFactory {
        return ProductListViewModelFactory(
            ProductsRepository(Generator()), ProductsDisplayModeRepository(storage), okHttpClient
        )
    }
}