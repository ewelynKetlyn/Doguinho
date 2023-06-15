package com.example.randomdog.network

import com.example.randomdog.model.BreedsResponse
import com.example.randomdog.model.Dog
import retrofit2.Response

class Repository {
    suspend fun getDog(): Response<Dog> {
        return RetrofitInstance.api.getRandomDog()
    }
    suspend fun getAllBreeds(): Response<BreedsResponse>{
        return RetrofitInstance.api.getAllBreeds()
    }
}