package com.example.newappkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newappkotlin.Utility.StringKs
import com.example.newappkotlin.Utility.UtilityKs
import com.example.newappkotlin.adapter.AdapterKotlinNews
import com.example.newappkotlin.databinding.ActivityMainBinding
import com.example.newappkotlin.model.Article
import com.example.newappkotlin.model.NewsModelKotlin
import com.example.newappkotlin.viewModel.NewsViewModel

class MainActivity : AppCompatActivity(), AdapterKotlinNews.ClickOnCard {

    private lateinit var articles : ArrayList<Article>
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       val  viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        UtilityKs.startShimmer(binding.shimmerView)
        viewModel.getNewsList().observe(this,
            { t ->
                UtilityKs.stopShimmer(binding.shimmerView)
                if(t?.status.equals(StringKs.STATUS_API)) {
                    articles = t?.articles!!
                    setAdapter(articles)
                } else {
                    Toast.makeText(this,"No Data Available", Toast.LENGTH_SHORT).show()
                }
            })

        viewModel.apiCall()

    }

    fun setAdapter(articles: ArrayList<Article>) {

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.newsShow.layoutManager= layoutManager
        val adapterKotlinNews = AdapterKotlinNews(this)
        binding.newsShow.adapter = adapterKotlinNews
        adapterKotlinNews.setList(articles)
    }

    override fun onPause() {
        super.onPause()
        UtilityKs.stopShimmer(binding.shimmerView)
    }

    override fun onclick(url: String) {
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}