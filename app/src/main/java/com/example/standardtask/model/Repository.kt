package com.example.standardtask.model

import com.example.standardtask.model.models.BodySent
import com.example.standardtask.network.RetrofitInterface

class Repository(private val retrofitInterface: RetrofitInterface) {

    suspend fun getBannerImages(lang: String, body: BodySent) = retrofitInterface.geBannerImages(lang, body)
}