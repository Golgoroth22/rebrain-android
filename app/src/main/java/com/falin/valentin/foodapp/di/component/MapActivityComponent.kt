package com.falin.valentin.foodapp.di.component

import com.falin.valentin.foodapp.di.module.MapActivityViewModelFactoryModule
import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.screen.map.MapActivity
import dagger.Subcomponent

/**
 * Dagger2 [Component] app interface for work with [MapActivity].
 *
 */
@Subcomponent(
    modules = [MapActivityViewModelFactoryModule::class]
)
@PerScreen
interface MapActivityComponent {
    /**
     * This method can be called for inject in [MapActivity]
     *
     */
    fun inject(activity: MapActivity)
}