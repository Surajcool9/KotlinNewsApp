package com.example.newappkotlin.network

import com.example.newappkotlin.model.NewsModelKotlin
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/v2/top-headlines")
    suspend fun getNewsArticles(@Query("country") country: String, @Query("apiKey") apiKey: String) : NewsModelKotlin

}