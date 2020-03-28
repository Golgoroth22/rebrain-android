package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.UserDataStorageModule
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.screen.main.AccountFragment
import dagger.Subcomponent


/**
 * Dagger2 [Component] app interface for work with [AccountFragment].
 *
 */
@Subcomponent(
    modules = [UserDataStorageModule::class]
)
@PerScreen
interface AccountComponent {
    /**
     * This method can be called for inject in [AccountTabFragment]
     *
     */
    fun inject(fragment: AccountFragment)
}