package com.example.newappkotlin.network

import com.example.newappkotlin.Utility.StringKs
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        // it intializes when the class is loaded first time

        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(StringKs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build();
        }
    }
}