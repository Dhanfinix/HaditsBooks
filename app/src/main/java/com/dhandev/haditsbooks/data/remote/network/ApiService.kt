package com.dhandev.haditsbooks.data.remote.network

import com.dhandev.haditsbooks.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("books/{id}/{number}")
    fun getHadith(
        @Path("id") id: String,
        @Path("number") number: String
    ): Call<ResponseSpecificId>

    @GET("books")
    fun getHadithBook(
    ): Call<ResponseHadithBook>

    @GET("books/{id}")
    fun getHadithList(
        @Path("id") id: String,
        @Query("range") range: String
    ): Call<ResponseHadithList>
}