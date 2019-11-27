package com.falin.valentin.foodapp.di.module

import android.content.Context
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.interactor.Storage
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 module for providing [ProductModeStorage].
 *
 */
@Module
class ProductModeStorageModule {

    /**
     * This method can be called for get [ProductModeStorage].
     *
     * @return [ProductModeStorage]
     */
    @Provides
    @Singleton
    fun provideStorage(context: Context): Storage<Int> {
        return ProductModeStorage(PreferencesHelper(context))
    }
}