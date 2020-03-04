package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.network.retrofit.service.ProductsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Dagger2 module for providing api methods for [Retrofit].
 *
 */
@Module
class RetrofitApiModule {
    /**
     * This method can be called for get [ProductsService].
     *
     * @return [ProductsService]
     */
    @Provides
    @PerApplication
    fun provideProductsService(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }
}