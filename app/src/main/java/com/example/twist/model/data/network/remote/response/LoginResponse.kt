package com.example.twist.model.data.network.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Token(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("fullname")
	val fullname: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("token")
	val token: String,

	@field:SerializedName("username")
	val username: String
)

data class Data(

	@field:SerializedName("token")
	val token: Token
)
