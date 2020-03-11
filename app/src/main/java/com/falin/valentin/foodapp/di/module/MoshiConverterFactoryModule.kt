package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Dagger2 module for providing [MoshiConverterFactory].
 *
 */
@Module
class MoshiConverterFactoryModule {

    /**
     * This method can be called for get [MoshiConverterFactory].
     *
     * @return [MoshiConverterFactory]
     */
    @Provides
    @PerApplication
    fun provideMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    }
}