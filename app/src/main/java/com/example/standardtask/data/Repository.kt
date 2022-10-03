package com.example.standardtask.data

import com.example.standardtask.data.models.send.BodySent
import com.example.standardtask.network.RetrofitInterface
import javax.inject.Inject

class Repository @Inject constructor(private val retrofitInterface: RetrofitInterface) {

    suspend fun getBannerImages(lang: String, body: BodySent) = retrofitInterface.getBannerImages(lang, body)
    suspend fun geCategories(lang: String) = retrofitInterface.getCategories(lang)
    suspend fun getHomePageComponents(lang: String, body: BodySent) = retrofitInterface.getHomePageComponents(lang, body)
}