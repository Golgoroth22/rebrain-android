package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.network.retrofit.service.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Dagger2 module for providing api methods for [Retrofit].
 *
 */
@Module
class RetrofitApiModule {
    /**
     * This method can be called for get [ProductsService].
     *
     * @return [ProductsService]
     */
    @Provides
    @PerApplication
    fun provideProductsService(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }

    /**
     * This method can be called for get [LoginService].
     *
     * @return [LoginService]
     */
    @Provides
    @PerApplication
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    /**
     * This method can be called for get [UserAvatarService].
     *
     * @return [UserAvatarService]
     */
    @Provides
    @PerApplication
    fun provideUserAvatarService(retrofit: Retrofit): UserAvatarService {
        return retrofit.create(UserAvatarService::class.java)
    }

    /**
     * This method can be called for get [UserService].
     *
     * @return [UserService]
     */
    @Provides
    @PerApplication
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    /**
     * This method can be called for get [PickupsService].
     *
     * @return [PickupsService]
     */
    @Provides
    @PerApplication
    fun providePickupsService(retrofit: Retrofit): PickupsService {
        return retrofit.create(PickupsService::class.java)
    }
}