package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.di.scope.PerScreen
import com.falin.valentin.foodapp.interactor.UserDataStorage
import com.falin.valentin.foodapp.network.retrofit.service.PickupsService
import com.falin.valentin.foodapp.repository.MapActivityRepository
import com.falin.valentin.foodapp.viewmodel.factories.MapActivityViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module for providing [MapActivityViewModelFactory].
 *
 */
@Module
class MapActivityViewModelFactoryModule {

    /**
     * This method can be called for get [MapActivityViewModelFactory].
     *
     * @return [MapActivityViewModelFactory]
     */
    @Provides
    @PerScreen
    fun provideFactory(service: PickupsService, storage: UserDataStorage): MapActivityViewModelFactory {
        return MapActivityViewModelFactory(MapActivityRepository(storage, service))
    }
}