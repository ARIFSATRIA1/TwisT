package com.example.twist.model.data.params

data class RegisterRequest(
    val fullname: String,
    val email: String,
    val password: String,
    val confirm_password: String
)
