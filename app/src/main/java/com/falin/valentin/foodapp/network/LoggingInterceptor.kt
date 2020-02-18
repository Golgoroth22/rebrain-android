package com.falin.valentin.foodapp.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * [Interceptor]-subclass for logging requests with [Timber].
 *
 */
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        Timber.i("Sending request ${request.url} on ${chain.connection()} + ${request.headers}")
        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        Timber.i("Received response for ${response.request.url} in ${t2 - t1} + ${response.headers}")
        return response
    }
}