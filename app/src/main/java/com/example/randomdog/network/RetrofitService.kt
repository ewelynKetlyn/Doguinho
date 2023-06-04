package com.example.randomdog.network

import com.example.randomdog.model.BreedsResponse
import com.example.randomdog.model.Dog
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("image/random")
    suspend fun getRandomDog(): Response<Dog>

    @GET("list/all")
    suspend fun getAllBreeds(): Response<BreedsResponse>
}