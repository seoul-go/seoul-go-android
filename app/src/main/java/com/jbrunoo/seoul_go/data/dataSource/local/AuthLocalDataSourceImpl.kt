package com.jbrunoo.seoul_go.data.dataSource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jbrunoo.seoul_go.common.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(private val dataStore: DataStore<Preferences>): AuthLocalDataSource {
    override suspend fun saveAuth(socialType: Constants.SOCIAL_TYPE) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[SOCIAL_TYPE_KEY] = socialType.type
        }
    }

    override fun getAuth(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[SOCIAL_TYPE_KEY] ?: Constants.SOCIAL_TYPE.UNAUTHENTICATED.type
        }
    }

    companion object {
        val SOCIAL_TYPE_KEY = stringPreferencesKey("social_type")
    }
}