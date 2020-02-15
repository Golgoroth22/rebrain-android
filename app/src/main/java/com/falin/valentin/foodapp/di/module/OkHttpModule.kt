package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
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
        return OkHttpClient().newBuilder().addNetworkInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                val t1 = System.nanoTime()
                Timber.i("Sending request ${request.url} on ${chain.connection()} + ${request.headers}")
                val response = chain.proceed(request)
                val t2 = System.nanoTime()
                Timber.i("Received response for ${response.request.url} in ${t2 - t1} + ${response.headers}")
                return response
            }
        }).build()
    }
}