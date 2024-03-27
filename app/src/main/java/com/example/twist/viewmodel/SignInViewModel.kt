package com.example.twist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.twist.model.data.preferences.TokenModel
import com.example.twist.model.data.repository.Repository
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: Repository): ViewModel() {

    fun loginUser(
        email: String,
        password: String
    ) = repository.loginUser(email, password)

    fun saveToken(tokenModel: TokenModel){
        viewModelScope.launch {
            repository.saveToken(tokenModel)
        }
    }

    fun getToken(): LiveData<TokenModel> {
        return repository.getToken().asLiveData()
    }
}