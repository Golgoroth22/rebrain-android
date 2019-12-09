package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.utils.Generator
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductListViewModelFactoryModule {

    /**
     * This method can be called for get [ProductListViewModelFactory].
     *
     * @return [ProductListViewModelFactory]
     */
    @Provides
    @Singleton
    fun provideFactory(storage: ProductModeStorage): ProductListViewModelFactory {
        return ProductListViewModelFactory(
            ProductsRepository(Generator()), ProductsDisplayModeRepository(storage)
        )
    }
}