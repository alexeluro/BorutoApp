package com.inspiredcoda.boruto.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveOnboardingState(completed: Boolean)
    fun readOnboardingState(): Flow<Boolean>

}