package com.falin.valentin.foodapp.di.module

import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.repository.IntroInfoRepository
import com.falin.valentin.foodapp.viewmodel.IntroViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class IntroViewModelFactoryModule {

    /**
     * This method can be called for get [IntroViewModelFactory].
     *
     * @return [IntroViewModelFactory]
     */
    @Provides
    @Singleton
    fun provideFactory(storage: IntroDisplayStorage): IntroViewModelFactory {
        return IntroViewModelFactory(IntroInfoRepository(storage))
    }
}