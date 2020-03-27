package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.AuthorizationStorage
import com.falin.valentin.foodapp.interactor.Storage
import com.falin.valentin.foodapp.utils.PreferencesHelper
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [AuthorizationStorage].
 *
 */
@Module
class AuthorizationStorageModule {
    /**
     * This method can be called for get [AuthorizationStorage].
     *
     * @return [AuthorizationStorage]
     */
    @Provides
    @PerScreen
    fun provideStorage(preferencesHelper: PreferencesHelper): Storage<Boolean> {
        return AuthorizationStorage(preferencesHelper)
    }
}