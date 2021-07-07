package com.example.newappkotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappkotlin.Utility.StringKs
import com.example.newappkotlin.model.NewsModelKotlin
import com.example.newappkotlin.network.ApiInterface
import com.example.newappkotlin.network.NewsRepository
import com.example.newappkotlin.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    private var newsModel: MutableLiveData<NewsModelKotlin> = MutableLiveData<NewsModelKotlin>()
    fun getNewsList(): LiveData<NewsModelKotlin> {
        return newsModel
    }

     fun apiCall() {

         viewModelScope.launch {
             NewsRepository.getNews().catch { e ->
                 e.message?.let { Log.d("Error", it) }
             }.collect { response ->
                 newsModel.value = response
             }
     }
    }
}

