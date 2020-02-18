package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.network.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import timber.log.Timber

/**
 * Dagger2 module for providing [OkHttpClient].
 *
 */
@Module
class OkHttpModule {
    /**
     * This method can be called for get [OkHttpClient].
     *
     * @return [OkHttpClient]
     */
    @Provides
    @PerApplication
    fun provideClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addNetworkInterceptor(LoggingInterceptor()).build()
    }
}