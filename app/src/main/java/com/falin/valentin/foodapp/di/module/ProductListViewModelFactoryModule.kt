package com.falin.valentin.foodapp.di.module

import android.content.Context
import com.falin.valentin.foodapp.di.component.DaggerAppComponent
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
    fun provideFactory(context: Context): ProductListViewModelFactory {
        return ProductListViewModelFactory(
            ProductsRepository(Generator()),
            ProductsDisplayModeRepository(
                DaggerAppComponent.builder().appModule(AppModule(context)).build()
                    .productModeStorage()
            )
        )
    }
}