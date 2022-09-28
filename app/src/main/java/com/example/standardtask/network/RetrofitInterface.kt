package com.example.standardtask.network

import com.example.standardtask.model.models.received.CategoriesModel
import com.example.standardtask.model.models.received.MainSliderImagesModel
import com.example.standardtask.model.models.send.BodySent
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

    @GET("Categories/index")
    suspend fun geCategories(@Header("lang") lang: String): Response<CategoriesModel>

}