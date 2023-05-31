package com.example.randomdog.network

import com.example.randomdog.model.Dog
import retrofit2.Response

class Repository {
    suspend fun getDog(): Response<Dog> {
        return RetrofitInstance.api.getRandomDog()
    }
}