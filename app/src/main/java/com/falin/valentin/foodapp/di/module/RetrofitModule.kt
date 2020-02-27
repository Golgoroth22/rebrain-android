package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dagger2 module for providing [Retrofit].
 *
 */
@Module
class RetrofitModule {
    /**
     * This method can be called for get [Retrofit].
     *
     * @return [Retrofit]
     */
    @Provides
    @PerApplication
    fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * This method can be called for get retrofit Url.
     *
     * @return [Retrofit] url
     */
    @Provides
    @PerApplication
    fun provideUrl(): String {
        return "http://api.android.srwx.net/api/v2/"
    }
}