package com.example.wilhelm_paus.api

import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    @GET("api/cnn-news/nasional")
    fun getNationalNews(): Call<NewsResponse>
}
