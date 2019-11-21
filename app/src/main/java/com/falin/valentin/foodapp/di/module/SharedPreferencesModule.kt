package com.falin.valentin.foodapp.di.module

import android.content.Context
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [PreferencesHelper].
 *
 */
@Module
class SharedPreferencesModule {

    /**
     * This method can be called for get [PreferencesHelper].
     *
     * @return [PreferencesHelper]
     */
    @Provides
    fun providePreferences(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }
}