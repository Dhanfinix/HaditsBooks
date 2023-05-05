package com.dhandev.haditsbooks.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseHadithList(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val datas: Datas? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)
@Parcelize
data class HadithsItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("arab")
	val arab: String? = null,

	var expand: Boolean = false

) : Parcelable

data class Datas(

	@field:SerializedName("hadiths")
	val hadiths: List<HadithsItem>? = null,

	@field:SerializedName("requested")
	val requested: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("id")
	val id: String? = null
)
