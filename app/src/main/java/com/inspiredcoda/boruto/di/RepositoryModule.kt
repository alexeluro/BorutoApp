package com.inspiredcoda.boruto.di

import android.content.Context
import com.inspiredcoda.boruto.data.repository.DataStoreOperationsImpl
import com.inspiredcoda.boruto.data.repository.Repository
import com.inspiredcoda.boruto.domain.repository.DataStoreOperations
import com.inspiredcoda.boruto.domain.use_cases.UseCase
import com.inspiredcoda.boruto.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.inspiredcoda.boruto.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCase {
        return UseCase(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
        )
    }

}