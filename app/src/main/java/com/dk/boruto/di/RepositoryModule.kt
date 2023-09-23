package com.dk.boruto.di

import android.content.Context
import com.dk.boruto.data.repository.DataStoreOperationsImpl
import com.dk.boruto.data.repository.Repository
import com.dk.boruto.domain.repository.DataStoreOperations
import com.dk.boruto.domain.use_cases.UseCases
import com.dk.boruto.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.dk.boruto.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.dk.boruto.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ): UseCases {
        return UseCases(
            savedOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository)
        )
    }
}