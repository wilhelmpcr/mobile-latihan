package com.example.wilhelm_paus.api

data class NewsResponse(
    val success: Boolean,
    val data: NewsData
)

data class NewsData(
    val posts: List<NewsPost>
)

data class NewsPost(
    val title: String,
    val pubDate: String,
    val description: String,
    val thumbnail: String,
    val link: String
)
