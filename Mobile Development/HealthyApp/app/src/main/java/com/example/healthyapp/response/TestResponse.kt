package com.example.healthyapp.response

import com.google.gson.annotations.SerializedName

data class TestResponse(
	@SerializedName("message")
	val message: String? = null,

	@SerializedName("status")
	val status: String? = null
)
