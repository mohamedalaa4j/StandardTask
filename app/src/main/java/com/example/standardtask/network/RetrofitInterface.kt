package com.example.standardtask.network

import com.example.standardtask.model.models.BodySent
import com.example.standardtask.model.models.MainSliderImagesModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitInterface {

    @POST("MobileMainPage/GetMainSliders/")
    suspend fun geBannerImages(@Header("lang") lang: String,
    @Body bodySent: BodySent
    ): Response<MainSliderImagesModel>

}