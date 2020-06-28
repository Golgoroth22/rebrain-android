package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.IntroDisplayStorageModule
import com.falin.valentin.foodapp.di.module.IntroViewModelFactoryModule
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.screen.splash.SplashActivity
import com.falin.valentin.foodapp.viewmodel.factories.IntroViewModelFactory
import dagger.Subcomponent

/**
 * Dagger2 [Component] app interface for work with [SplashActivity].
 *
 */
@Subcomponent(
    modules = [IntroDisplayStorageModule::class, IntroViewModelFactoryModule::class]
)
@PerScreen
interface SplashComponent {
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
     * This method can be called for inject in [SplashActivity]
     *
     */
    fun inject(splashActivity: SplashActivity)
}