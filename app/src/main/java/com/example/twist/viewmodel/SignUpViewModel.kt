package com.example.twist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twist.model.data.repository.Repository

class SignUpViewModel(private val repository: Repository): ViewModel() {

    fun registerUser(
        fullname: String,
        email: String,
        password: String,
        confirm_password: String
    ) = repository.registerUser(fullname, email, password, confirm_password)



}