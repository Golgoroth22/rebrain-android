package com.falin.valentin.foodapp.di.component

import android.content.Context
import com.falin.valentin.foodapp.di.module.*
import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Component

/**
 * Dagger2 [Component] app interface.
 *
 */
@PerApplication
@Component(
    modules = [SharedPreferencesModule::class, AppModule::class]
)
interface AppComponent {
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

    /**
     * This method can be called for init [MainTabComponent] dagger subcomponent.
     *
     * @return [PreferencesHelper]
     */
    fun initMainTabComponent(
        productListViewModelFactoryModule: ProductListViewModelFactoryModule,
        productModeStorageModule: ProductModeStorageModule
    ): MainTabComponent

    fun initSplashComponent(
        introDisplayStorageModule: IntroDisplayStorageModule,
        introViewModelFactoryModule: IntroViewModelFactoryModule
    ): SplashComponent

    fun initIntroComponent(
        introDisplayStorageModule: IntroDisplayStorageModule,
        introViewModelFactoryModule: IntroViewModelFactoryModule
    ): IntroComponent
}