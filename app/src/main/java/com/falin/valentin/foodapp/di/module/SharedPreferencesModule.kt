package com.falin.valentin.foodapp.di.module

import android.content.Context
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    @Provides
    fun providePreferences(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }
}