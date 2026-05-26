package com.example.wilhelm_paus.data.api

import com.example.wilhelm_paus.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}
