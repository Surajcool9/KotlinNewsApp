package com.example.newappkotlin.network

import com.example.newappkotlin.Utility.StringKs
import com.example.newappkotlin.model.NewsModelKotlin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository {

    companion object {

        fun getNews(): Flow<NewsModelKotlin> = flow {
            val response = RetrofitInstance.api.getNewsArticles(StringKs.INDIA_CODE, StringKs.API_KEY)
            emit(response)
        }
    }
}