package com.falin.valentin.foodapp.di.component

import android.content.Context
import com.falin.valentin.foodapp.di.module.*
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger2 [Component] app interface.
 *
 */
@Singleton
@Component(
    modules = [IntroDisplayStorageModule::class, ProductModeStorageModule::class, SharedPreferencesModule::class, AppModule::class]
)
interface AppComponent {
    /**
     * This method can be called for get [ProductModeStorage].
     *
     * @return [ProductModeStorage]
     */
    fun productModeStorage(): ProductModeStorage

    /**
     * This method can be called for get [IntroDisplayStorage].
     *
     * @return [IntroDisplayStorage]
     */
    fun introDisplayStorage(): IntroDisplayStorage

    /**
     * This method can be called for get [PreferencesHelper].
     *
     * @return [PreferencesHelper]
     */
    fun sharedPreferencesStorage(): PreferencesHelper

    /**
     * This method can be called for get app [Context].
     *
     * @return [Context]
     */
    fun context(): Context
}