package com.example.randomdog.network

import com.example.randomdog.model.Dog
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("random")
    suspend fun getRandomDog(): Response<Dog>
}