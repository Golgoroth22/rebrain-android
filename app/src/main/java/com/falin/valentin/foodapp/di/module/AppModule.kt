package com.falin.valentin.foodapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 module for providing [Context].
 *
 */
@Module
class AppModule(
    val context: Context
) {
    /**
     * This method can be called for get app [Context].
     *
     * @return [Context]
     */
    @Provides
    @Singleton
    fun provideContext() = context
}