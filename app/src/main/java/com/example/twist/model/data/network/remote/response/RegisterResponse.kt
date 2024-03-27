package com.example.twist.model.data.network.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("message")
	val message: String
)
