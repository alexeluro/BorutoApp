package com.inspiredcoda.boruto.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.inspiredcoda.boruto.constants.Constants.DataStore.PREFERENCES_KEY
import com.inspiredcoda.boruto.constants.Constants.DataStore.PREFERENCES_NAME
import com.inspiredcoda.boruto.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl @Inject constructor(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnboardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onboardingState = preferences[PreferencesKey.onBoardingKey] ?: false
            onboardingState
        }
    }
}