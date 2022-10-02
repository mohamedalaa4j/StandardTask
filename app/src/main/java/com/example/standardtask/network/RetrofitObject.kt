package com.example.standardtask.network

import com.example.standardtask.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitObject {

    private val retrofitInitialization: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofit: RetrofitInterface by lazy {
        retrofitInitialization.create(RetrofitInterface::class.java)
    }

}