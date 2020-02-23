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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.android.srwx.net/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}