package com.inspiredcoda.boruto.data.repository

import com.inspiredcoda.boruto.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnboardingState(completed)
    }


    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnboardingState()
    }

}