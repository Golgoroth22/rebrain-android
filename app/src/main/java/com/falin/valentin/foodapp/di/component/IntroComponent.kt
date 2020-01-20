package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.IntroDisplayStorageModule
import com.falin.valentin.foodapp.di.module.IntroViewModelFactoryModule
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.viewmodel.IntroViewModelFactory
import dagger.Component
import dagger.Subcomponent

/**
 * Dagger2 [Component] app interface for work with [IntroActivity].
 *
 */
@Subcomponent(
    modules = [IntroDisplayStorageModule::class, IntroViewModelFactoryModule::class]
)
@PerScreen
interface IntroComponent {
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
     * This method can be called for inject in [IntroActivity]
     *
     */
    fun inject(introActivity: IntroActivity)
}