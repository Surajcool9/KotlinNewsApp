package com.example.newappkotlin.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newappkotlin.R
import com.example.newappkotlin.databinding.NewsListviewBinding
import com.example.newappkotlin.model.Article
import java.util.*

class AdapterKotlinNews(val clickOnCard: ClickOnCard) : RecyclerView.Adapter<AdapterKotlinNews.NewsViewHolder>() {
    lateinit var articles: ArrayList<Article>
    private lateinit var context: Context

    companion object {
        var clickOnCard : ClickOnCard? = null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        context = parent.context
        return NewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.news_listview,
                parent,
                false
            )
        )
    }

    class NewsViewHolder(val binding: NewsListviewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (!TextUtils.isEmpty(articles[position].title)) {
            holder.binding.newTitle.text = articles[position].title
        } else {
            holder.binding.newTitle.visibility = View.GONE
        }

        if (!TextUtils.isEmpty(articles[position].urlToImage)) {
            Glide.with(context)
                .load(articles[position].urlToImage)
                .thumbnail(0.5f)
                .placeholder(R.drawable.glide_placeholder)
                .centerCrop()
                .into(holder.binding.newsImage)
        } else {
            holder.binding.newsImage.setImageDrawable(context.getDrawable(R.drawable.ic_placeholder_violet))
        }

        holder.binding.newsCard.setOnClickListener {
            clickOnCard.onclick(articles[position].url)
        }
    }

    override fun getItemCount(): Int {
        if (articles.size > 0)
            return articles.size
        else return 0
    }

    fun setList(articles: ArrayList<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    interface ClickOnCard {
        fun onclick(url: String)
    }

}