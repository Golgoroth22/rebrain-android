package com.falin.valentin.foodapp.di.module

import android.content.Context
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
    fun provideStorage(context: Context): Storage<Boolean> {
        return IntroDisplayStorage(PreferencesHelper(context))
    }
}