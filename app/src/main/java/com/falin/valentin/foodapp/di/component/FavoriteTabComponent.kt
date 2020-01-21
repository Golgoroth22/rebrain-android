package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.FavoriteProductListViewModelFactoryModule
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.screen.main.FavoriteTabFragment
import com.falin.valentin.foodapp.screen.main.MainTabFragment
import dagger.Component
import dagger.Subcomponent

/**
 * Dagger2 [Component] app interface for work with [MainTabFragment].
 *
 */
@Subcomponent(
    modules = [FavoriteProductListViewModelFactoryModule::class]
)
@PerScreen
interface FavoriteTabComponent {
    /**
     * This method can be called for inject in [FavoriteTabFragment]
     *
     */
    fun inject(favoriteTabFragment: FavoriteTabFragment)
}