package com.dhandev.haditsbooks.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseHadithBook(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class DataItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

)
