package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.repository.FavoriteProductsRepository
import com.falin.valentin.foodapp.utils.Generator
import com.falin.valentin.foodapp.viewmodel.FavoriteProductListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [FavoriteProductListViewModelFactory].
 *
 */
@Module
class FavoriteProductListViewModelFactoryModule {
    /**
     * This method can be called for get [FavoriteProductListViewModelFactory].
     *
     * @return [FavoriteProductListViewModelFactory]
     */
    @Provides
    @PerScreen
    fun provideFactory(): FavoriteProductListViewModelFactory {
        return FavoriteProductListViewModelFactory(FavoriteProductsRepository(Generator()))
    }
}