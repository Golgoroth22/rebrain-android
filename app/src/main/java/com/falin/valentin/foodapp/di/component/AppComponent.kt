package com.falin.valentin.foodapp.di.component

import android.content.Context
import com.falin.valentin.foodapp.di.module.*
import com.falin.valentin.foodapp.di.scope.PerApplication
import com.falin.valentin.foodapp.interactor.FavoriteProductsStorage
import com.falin.valentin.foodapp.repository.ProductsRepository
import com.falin.valentin.foodapp.screen.splash.SplashActivity
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Dagger2 [Component] app interface.
 *
 */
@PerApplication
@Component(
    modules = [SharedPreferencesModule::class, AppModule::class, FavoriteProductsStorageModule::class, OkHttpModule::class, RetrofitModule::class, RetrofitApiModule::class, MoshiConverterFactoryModule::class]
)
interface AppComponent {
    /**
     * This method can be called for get [PreferencesHelper].
     *
     * @return [PreferencesHelper]
     */
    fun sharedPreferencesStorage(): PreferencesHelper

    /**
     * This method can be called for get app [Context].
     *
     * @return [Context]
     */
    fun context(): Context

    /**
     * This method can be called for get [FavoriteProductsStorage].
     *
     * @return [FavoriteProductsStorage]
     */
    fun favoriteProductsStorage(): FavoriteProductsStorage

    /**
     * This method can be called for get [OkHttpClient].
     *
     * @return [OkHttpClient]
     */
    fun okHttpClient(): OkHttpClient

    /**
     * This method can be called for init [MainTabComponent] dagger subcomponent.
     *
     * @return [PreferencesHelper]
     */
    fun initMainTabComponent(
        productListViewModelFactoryModule: ProductListViewModelFactoryModule,
        productModeStorageModule: ProductModeStorageModule
    ): MainTabComponent

    /**
     * This method can be called for init [FavoriteTabComponent] dagger subcomponent.
     *
     * @return [FavoriteTabComponent]
     */
    fun initFavoriteTabComponent(
        favoriteProductListViewModelFactoryModule: FavoriteProductListViewModelFactoryModule
    ): FavoriteTabComponent

    /**
     * This method can be called for init [SplashComponent] dagger subcomponent.
     *
     * @return [SplashComponent]
     */
    fun initSplashComponent(
        introDisplayStorageModule: IntroDisplayStorageModule,
        introViewModelFactoryModule: IntroViewModelFactoryModule
    ): SplashComponent

    /**
     * This method can be called for init [IntroComponent] dagger subcomponent.
     *
     * @return [IntroComponent]
     */
    fun initIntroComponent(
        introDisplayStorageModule: IntroDisplayStorageModule,
        introViewModelFactoryModule: IntroViewModelFactoryModule
    ): IntroComponent

    /**
     * This method can be called for init [AuthorizationComponent] dagger subcomponent.
     *
     * @return [AuthorizationComponent]
     */
    fun initAuthComponent(
        authorizationFragmentViewModelFactoryModule: AuthorizationFragmentViewModelFactoryModule
    ): AuthorizationComponent

    /**
     * This method can be called for init [AuthorizationComponent] dagger subcomponent.
     *
     * @return [AuthorizationComponent]
     */
    fun initAccountTabComponent(
        accountTabFragmentViewModelFactoryModule: AccountTabFragmentViewModelFactoryModule,
        authorizationStorageModule: AuthorizationStorageModule
    ): AccountTabComponent

    /**
     * This method can be called for init [AccountComponent] dagger subcomponent.
     *
     * @return [AccountComponent]
     */
    fun initAccountComponent(
        userDataStorageModule: UserDataStorageModule
    ): AccountComponent

    /**
     * This method can be called for inject in [ProductsRepository]
     *
     */
    fun inject(repository: ProductsRepository)
}