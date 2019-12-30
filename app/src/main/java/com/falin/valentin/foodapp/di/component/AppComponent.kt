package com.falin.valentin.foodapp.di.component

import android.content.Context
import com.falin.valentin.foodapp.di.module.*
import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.screen.main.MainTabFragment
import com.falin.valentin.foodapp.screen.splash.SplashActivity
import com.falin.valentin.foodapp.utils.PreferencesHelper
import com.falin.valentin.foodapp.viewmodel.IntroViewModelFactory
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import dagger.Component
import javax.inject.Singleton

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
     * This method can be called for init [ScreenComponent] dagger subcomponent.
     *
     * @return [PreferencesHelper]
     */
    fun initScreenComponent(
        introDisplayStorageModule: IntroDisplayStorageModule,
        introViewModelFactoryModule: IntroViewModelFactoryModule,
        productListViewModelFactoryModule: ProductListViewModelFactoryModule,
        productModeStorageModule: ProductModeStorageModule
    ): ScreenComponent
}