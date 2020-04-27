package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.AuthorizationFragmentViewModelFactoryModule
import com.falin.valentin.foodapp.di.module.AuthorizationStorageModule
import com.falin.valentin.foodapp.di.module.IntroDisplayStorageModule
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.screen.main.AuthorizationFragment
import dagger.Subcomponent

/**
 * Dagger2 [Component] app interface for work with [AuthorizationFragment].
 *
 */
@Subcomponent(
    modules = [AuthorizationFragmentViewModelFactoryModule::class, AuthorizationStorageModule::class]
)
@PerScreen
interface AuthorizationComponent {
    /**
     * This method can be called for inject in [FavoriteTabFragment]
     *
     */
    fun inject(fragment: AuthorizationFragment)
}