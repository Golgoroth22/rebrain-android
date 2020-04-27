package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.UserDataStorage
import com.falin.valentin.foodapp.repository.AccountFragmentRepository
import com.falin.valentin.foodapp.viewmodel.AccountFragmentViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [AccountFragmentViewModelFactory].
 *
 */
@Module
class AccountFragmentViewModelFactoryModule {
    /**
     * This method can be called for get [AccountFragmentViewModelFactory].
     *
     * @return [AccountFragmentViewModelFactory]
     */
    @Provides
    @PerScreen
    fun provideFactory(storage: UserDataStorage): AccountFragmentViewModelFactory {
        return AccountFragmentViewModelFactory(AccountFragmentRepository(storage))
    }
}