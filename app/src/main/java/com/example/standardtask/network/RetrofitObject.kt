package com.example.standardtask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitObject {

    private const val BASE_URL = "https://satatechnologygroup.net:3301/api/"

    private val retrofitInitialization: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofit : RetrofitInterface by lazy {
        retrofitInitialization.create(RetrofitInterface::class.java)
    }


}