package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.*
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.ProductModeStorage
import com.falin.valentin.foodapp.screen.main.MainTabFragment
import com.falin.valentin.foodapp.viewmodel.ProductListViewModelFactory
import dagger.Component
import dagger.Subcomponent

/**
 * Dagger2 [Component] app interface for work with [MainTabFragment].
 *
 */
@Subcomponent(
    modules = [ProductModeStorageModule::class, ProductListViewModelFactoryModule::class]
)
@PerScreen
interface MainTabComponent {
    /**
     * This method can be called for get [ProductModeStorage].
     *
     * @return [ProductModeStorage]
     */
    fun productModeStorage(): ProductModeStorage

    /**
     * This method can be called for get [ProductListViewModelFactory].
     *
     * @return [ProductListViewModelFactory]
     */
    fun productListViewModelFactory(): ProductListViewModelFactory

    /**
     * This method can be called for inject in [MainTabFragment]
     *
     */
    fun inject(mainTabFragment: MainTabFragment)
}