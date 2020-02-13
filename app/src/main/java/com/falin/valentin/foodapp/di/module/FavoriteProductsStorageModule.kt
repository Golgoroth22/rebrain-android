package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.interactor.FavoriteProductsStorage
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [FavoriteProductsStorage].
 *
 */
@Module
class FavoriteProductsStorageModule {

    /**
     * This method can be called for get [FavoriteProductsStorage].
     *
     * @return [FavoriteProductsStorage]
     */
    @Provides
    @PerApplication
    fun provideStorage(): FavoriteProductsStorage {
        return FavoriteProductsStorage()
    }
}