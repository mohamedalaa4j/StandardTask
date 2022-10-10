package com.example.standardtask.data

import com.example.standardtask.data.models.send.BodySent
import com.example.standardtask.network.RetrofitInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofitInterface: RetrofitInterface,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getBannerImages(lang: String, body: BodySent) =
        withContext(ioDispatcher) { retrofitInterface.getBannerImages(lang, body) }

    suspend fun geCategories(lang: String) = withContext(ioDispatcher) { retrofitInterface.getCategories(lang) }

    suspend fun getHomePageComponents(lang: String, body: BodySent) =
        withContext(ioDispatcher) { retrofitInterface.getHomePageComponents(lang, body) }
}