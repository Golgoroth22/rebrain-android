package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.*
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.screen.main.MainTabFragment
import com.falin.valentin.foodapp.screen.splash.SplashActivity
import com.falin.valentin.foodapp.viewmodel.IntroViewModelFactory
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import dagger.Component
import dagger.Subcomponent

/**
 * Dagger2 [Component] for some screens.
 *
 */
@Subcomponent(
    modules = [IntroDisplayStorageModule::class, ProductModeStorageModule::class, IntroViewModelFactoryModule::class, ProductListViewModelFactoryModule::class]
)
@PerScreen
interface ScreenComponent {
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
     * This method can be called for get [IntroViewModelFactory].
     *
     * @return [IntroViewModelFactory]
     */
    fun introViewModelFactory(): IntroViewModelFactory

    /**
     * This method can be called for get [ProductListViewModelFactory].
     *
     * @return [ProductListViewModelFactory]
     */
    fun productListViewModelFactory(): ProductListViewModelFactory

    /**
     * This method can be called for inject in [IntroActivity]
     *
     */
    fun inject(introActivity: IntroActivity)

    /**
     * This method can be called for inject in [SplashActivity]
     *
     */
    fun inject(splashActivity: SplashActivity)

    /**
     * This method can be called for inject in [MainTabFragment]
     *
     */
    fun inject(mainTabFragment: MainTabFragment)
}