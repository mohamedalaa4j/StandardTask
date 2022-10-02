package com.example.standardtask.network

import com.example.standardtask.data.models.received.CategoriesModel
import com.example.standardtask.data.models.received.MainSliderImagesModel
import com.example.standardtask.data.models.send.BodySent
import com.example.standardtask.utilities.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitInterface {

    @POST(Constants.MAIN_SLIDERS_ENDPOINT)
    suspend fun geBannerImages(@Header("lang") lang: String,
    @Body bodySent: BodySent
    ): Response<MainSliderImagesModel>

    @GET(Constants.CATEGORIES_ENDPOINT)
    suspend fun geCategories(@Header("lang") lang: String): Response<CategoriesModel>

}