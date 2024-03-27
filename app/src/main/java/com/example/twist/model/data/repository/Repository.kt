package com.example.twist.model.data.repository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.twist.model.data.ResultState
import com.example.twist.model.data.database.VideoDatabase
import com.example.twist.model.data.network.remote.response.ErrorResponse
import com.example.twist.model.data.network.remote.response.LoginResponse
import com.example.twist.model.data.network.remote.retrofit.ApiService
import com.example.twist.model.data.params.LoginRequest
import com.example.twist.model.data.preferences.TokenModel
import com.example.twist.model.data.preferences.TokenPreferences
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class Repository (
    private val apiService: ApiService,
    private val tokenPreferences: TokenPreferences,
//    private val videoDatabase: VideoDatabase
) {

    fun loginUser(
        email: String,
        password: String
    ): LiveData<ResultState<LoginResponse>> = liveData {
        emit(ResultState.Loading)
        try {
            val user = apiService.login(LoginRequest(email, password))
            tokenPreferences.saveToken(TokenModel(user.data.token.token))
            emit(ResultState.Success(user))
        }catch (e: HttpException) {
            val erorrBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(erorrBody, ErrorResponse::class.java)
            emit(ResultState.Error(errorResponse.message!!))
        }
    }





    fun getToken(): Flow<TokenModel> {
        return tokenPreferences.getToken()
    }

    suspend fun saveToken(tokenModel: TokenModel) {
        tokenPreferences.saveToken(tokenModel)
    }

    suspend fun logout() {
        tokenPreferences.logout()
    }
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(apiService: ApiService, tokenPreferences: TokenPreferences): Repository  = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Repository(apiService, tokenPreferences)
        }.also { INSTANCE = it }
    }
}