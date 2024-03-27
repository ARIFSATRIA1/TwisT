package com.example.twist.model.data.preferences

import android.content.Context
import android.media.session.MediaSession.Token
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
class TokenPreferences private constructor(private val dataStore: DataStore<Preferences>){

    fun getToken(): Flow<TokenModel> {
        return dataStore.data.map { preferences ->
            TokenModel(
                preferences[TOKEN] ?: "",
                preferences[IS_LOGIN] ?: true
            )
        }
    }

    suspend fun saveToken(token: TokenModel) {
        dataStore.edit {mutablePreferences ->
            mutablePreferences[TOKEN] ?: token.token
            mutablePreferences[IS_LOGIN] = token.isLogin
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: TokenPreferences? = null

        private val TOKEN = stringPreferencesKey("token")
        private val IS_LOGIN = booleanPreferencesKey("IsLogin")

        fun getInstance(dataStore: DataStore<Preferences>):TokenPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = TokenPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
