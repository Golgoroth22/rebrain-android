package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.UserDataStorage
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing user data.
 *
 */
@Module
class UserDataStorageModule {
    /**
     * This method can be called for get [UserDataStorage].
     *
     * @return [UserDataStorage]
     */
    @Provides
    @PerScreen
    fun provideUserData(preferencesHelper: PreferencesHelper): UserDataStorage {
        return UserDataStorage(preferencesHelper)
    }
}