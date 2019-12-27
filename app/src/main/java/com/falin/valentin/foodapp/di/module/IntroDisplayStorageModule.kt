package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.interactor.Storage
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
    @Singleton
    fun provideStorage(preferencesHelper: PreferencesHelper): Storage<Boolean> {
        return IntroDisplayStorage(preferencesHelper)
    }
}