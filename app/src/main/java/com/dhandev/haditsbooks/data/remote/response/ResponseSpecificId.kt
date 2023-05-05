package com.dhandev.haditsbooks.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseSpecificId(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class Contents(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("arab")
	val arab: String? = null
)

data class Data(

	@field:SerializedName("contents")
	val contents: Contents? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("id")
	val id: String? = null
)
