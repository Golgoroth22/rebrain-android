package com.falin.valentin.foodapp.di.module

import android.content.Context
import com.falin.valentin.foodapp.di.component.DaggerAppComponent
import com.falin.valentin.foodapp.repository.IntroInfoRepository
import com.falin.valentin.foodapp.viewmodel.IntroViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class IntroViewModelFactoryModule {

    /**
     * TODO
     *
     */
    @Provides
    @Singleton
    fun provideFactory(context: Context): IntroViewModelFactory {
        return IntroViewModelFactory(
            IntroInfoRepository(
                DaggerAppComponent.builder().appModule(
                    AppModule(context)
                ).build().introDisplayStorage()
            )
        )
    }
}