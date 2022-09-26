package com.example.standardtask.network

import com.example.standardtask.model.BodySent
import com.example.standardtask.model.MainSliderImagesModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitInterface {

    @POST("MobileMainPage/GetMainSliders/")
    suspend fun getMainSliderImages(@Header("lang") lang: String,
    @Body bodySent: BodySent
    ): Response<MainSliderImagesModel>

}