package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.AccountTabFragmentViewModelFactoryModule
import com.falin.valentin.foodapp.di.module.AuthorizationStorageModule
import com.falin.valentin.foodapp.di.module.UserDataStorageModule
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.screen.main.AccountTabFragment
import dagger.Subcomponent


/**
 * Dagger2 [Component] app interface for work with [AccountTabFragment].
 *
 */
@Subcomponent(
    modules = [AccountTabFragmentViewModelFactoryModule::class, AuthorizationStorageModule::class]
)
@PerScreen
interface AccountTabComponent {
    /**
     * This method can be called for inject in [AccountTabFragment]
     *
     */
    fun inject(fragment: AccountTabFragment)
}