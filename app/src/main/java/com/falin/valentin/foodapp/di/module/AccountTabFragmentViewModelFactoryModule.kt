package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.AuthorizationStorage
import com.falin.valentin.foodapp.repository.AccountTabRepository
import com.falin.valentin.foodapp.viewmodel.AccountTabFragmentViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [AccountTabFragmentViewModelFactory].
 *
 */
@Module
class AccountTabFragmentViewModelFactoryModule {
    /**
     * This method can be called for get [AccountTabFragmentViewModelFactory].
     *
     * @return [AccountTabFragmentViewModelFactory]
     */
    @Provides
    @PerScreen
    fun provideFactory(
        storage: AuthorizationStorage
    ): AccountTabFragmentViewModelFactory {
        return AccountTabFragmentViewModelFactory(AccountTabRepository(storage))
    }
}