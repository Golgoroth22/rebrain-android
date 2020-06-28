package com.falin.valentin.foodapp.di.module

import android.content.Context
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.UserDataStorage
import com.falin.valentin.foodapp.network.retrofit.service.UserAvatarService
import com.falin.valentin.foodapp.network.retrofit.service.UserService
import com.falin.valentin.foodapp.repository.AccountFragmentRepository
import com.falin.valentin.foodapp.viewmodel.factories.AccountFragmentViewModelFactory
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
    fun provideFactory(
        context: Context,
        storage: UserDataStorage,
        avatarService: UserAvatarService,
        userService: UserService
    ): AccountFragmentViewModelFactory {
        return AccountFragmentViewModelFactory(
            AccountFragmentRepository(
                context,
                storage,
                avatarService,
                userService
            )
        )
    }
}