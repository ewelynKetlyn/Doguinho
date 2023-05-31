package com.example.randomdog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdog.model.Dog
import com.example.randomdog.network.Repository
import com.example.randomdog.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    private val retrofitService = RetrofitInstance.api
    val response: MutableLiveData<Response<Dog>> = MutableLiveData()

    init {
        getRandomDog()
    }

    fun getRandomDog(){
        viewModelScope.launch {
            response.value = retrofitService.getRandomDog()
        }
    }
}