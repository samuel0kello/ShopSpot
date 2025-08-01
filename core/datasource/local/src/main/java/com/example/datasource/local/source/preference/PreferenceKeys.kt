package com.example.datasource.local.source.preference

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
}