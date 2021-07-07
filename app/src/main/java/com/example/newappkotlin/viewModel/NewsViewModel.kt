package com.example.newappkotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappkotlin.Utility.StringKs
import com.example.newappkotlin.model.NewsModelKotlin
import com.example.newappkotlin.network.ApiInterface
import com.example.newappkotlin.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO) {
         apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
            val response = apiInterface.getNewsArticles(StringKs.INDIA_CODE, StringKs.API_KEY)
        //val call: Call<NewsModelKotlin> = apiInterface.getNewsArticles(StringKs.INDIA_CODE, StringKs.API_KEY)
        newsModel.postValue(response)
        }
        /*call.enqueue(object : Callback<NewsModelKotlin>{
            override fun onResponse(
                call: Call<NewsModelKotlin>,
                response: Response<NewsModelKotlin>
            ) {
                newsModel.postValue(response.body())
            }

            override fun onFailure(call: Call<NewsModelKotlin>, t: Throwable) {
                Log.e("error", t.message+" ")
            }

        })*/
    }
}