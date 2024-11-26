package com.example.newsx
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApInterface {
    @GET("top-headlines")
    fun getNews(
        @Query("country")country:String,
        @Query("category")category:String,
        @Query("apiKey")apiKey:String
    ): Call<NewsInfo>

}