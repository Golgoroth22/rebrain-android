package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.repository.ProductsDisplayModeRepository
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.utils.Generator
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ProductListViewModelFactoryModule {

    /**
     * This method can be called for get [ProductListViewModelFactory].
     *
     * @return [ProductListViewModelFactory]
     */
    @Provides
    @PerApplication
    fun provideFactory(storage: ProductModeStorage): ProductListViewModelFactory {
        return ProductListViewModelFactory(
            ProductsRepository(Generator()), ProductsDisplayModeRepository(storage)
        )
    }
}