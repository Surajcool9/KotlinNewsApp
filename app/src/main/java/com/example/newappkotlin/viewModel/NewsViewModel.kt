package com.example.newappkotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newappkotlin.Utility.StringKs
import com.example.newappkotlin.model.NewsModelKotlin
import com.example.newappkotlin.network.ApiInterface
import com.example.newappkotlin.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    private var newsModel: MutableLiveData<NewsModelKotlin> = MutableLiveData<NewsModelKotlin>()
    private lateinit var apiInterface:ApiInterface


    fun getNewsList(): LiveData<NewsModelKotlin> {
        return newsModel
    }

    fun apiCall() {
         apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val call: Call<NewsModelKotlin> = apiInterface.getNewsArticles(StringKs.INDIA_CODE, StringKs.API_KEY)

        call.enqueue(object : Callback<NewsModelKotlin>{
            override fun onResponse(
                call: Call<NewsModelKotlin>,
                response: Response<NewsModelKotlin>
            ) {
                newsModel.postValue(response.body())
            }

            override fun onFailure(call: Call<NewsModelKotlin>, t: Throwable) {
                Log.e("error", t.message+" ")
            }

        })
    }
}