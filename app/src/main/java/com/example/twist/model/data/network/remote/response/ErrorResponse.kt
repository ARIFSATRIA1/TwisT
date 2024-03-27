package com.example.twist.model.data.network.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null
)
