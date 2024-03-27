package com.example.twist.model.data.di

import android.content.Context
import com.example.twist.model.data.database.VideoDatabase
import com.example.twist.model.data.network.remote.retrofit.ApiConfig
import com.example.twist.model.data.preferences.TokenPreferences
import com.example.twist.model.data.preferences.dataStore
import com.example.twist.model.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = TokenPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()

        return Repository.getInstance(apiService,pref)
    }
}