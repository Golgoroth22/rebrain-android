package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.interactor.Storage
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [IntroDisplayStorage].
 *
 */
@Module
class IntroDisplayStorageModule {

    /**
     * This method can be called for get [IntroDisplayStorage].
     *
     * @return [IntroDisplayStorage]
     */
    @Provides
    @PerScreen
    fun provideStorage(preferencesHelper: PreferencesHelper): Storage<Boolean> {
        return IntroDisplayStorage(preferencesHelper)
    }
}