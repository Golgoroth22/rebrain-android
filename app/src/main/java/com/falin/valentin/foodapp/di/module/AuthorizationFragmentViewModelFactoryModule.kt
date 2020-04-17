package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.AuthorizationStorage
import com.falin.valentin.foodapp.network.retrofit.service.LoginService
import com.falin.valentin.foodapp.repository.AuthorizationRepository
import com.falin.valentin.foodapp.viewmodel.AuthorizationFragmentViewModelFactory
import com.falin.valentin.foodapp.viewmodel.FavoriteProductListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [AuthorizationFragmentViewModelFactory].
 *
 */
@Module
class AuthorizationFragmentViewModelFactoryModule {
    /**
     * This method can be called for get [FavoriteProductListViewModelFactory].
     *
     * @return [FavoriteProductListViewModelFactory]
     */
    @Provides
    @PerScreen
    fun provideFactory(
        storage: AuthorizationStorage,
        service: LoginService
    ): AuthorizationFragmentViewModelFactory {
        return AuthorizationFragmentViewModelFactory(AuthorizationRepository(storage, service))
    }
}