package com.example.newappkotlin.network

import com.example.newappkotlin.Utility.StringKs
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


        // it intializes when the class is loaded first time
    private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(StringKs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

    val api : ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

}