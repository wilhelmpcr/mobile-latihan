package com.example.wilhelm_paus.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wilhelm_paus.databinding.ItemNewsBinding

class NewsAdapter(private var newsList: List<NewsPost>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsPost) {
            binding.tvNewsTitle.text = news.title
            binding.tvNewsDate.text = news.pubDate
            Glide.with(binding.imgNews.context)
                .load(news.thumbnail)
                .into(binding.imgNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    fun updateData(newList: List<NewsPost>) {
        newsList = newList
        notifyDataSetChanged()
    }
}
