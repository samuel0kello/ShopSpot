package com.samuelokello.core.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.samuelokello.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val AUTH_KEY = stringPreferencesKey("auth_key")
        private val USER_DATA = stringPreferencesKey("user_data")
    }

    /** Save access token */
    suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_KEY] = accessToken
        }
    }

    /** Get access token */
    val getAccessToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[AUTH_KEY] ?: ""
    }

    /** Clear access token */
    suspend fun clearAccessToken() {
        dataStore.edit { preferences ->
            preferences.remove(AUTH_KEY)
        }
    }

    /** Save User Data */
    suspend fun saveUserData(user: UserData) {
        val jsonString = Json.encodeToString(user)
        dataStore.edit { preferences ->
            preferences[USER_DATA] = jsonString
        }
    }

    /** Get User Data */
    val getUserData: Flow<UserData?> = dataStore.data.map { preferences ->
        preferences[USER_DATA]?.let { jsonString ->
            Json.decodeFromString<UserData>(jsonString)
        }
    }
}
