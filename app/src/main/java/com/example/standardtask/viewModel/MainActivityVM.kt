package com.example.standardtask.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standardtask.model.Repository
import com.example.standardtask.model.models.received.CategoriesModel
import com.example.standardtask.model.models.send.BodySent
import com.example.standardtask.model.models.received.MainSliderImagesModel
import com.example.standardtask.network.RetrofitObject
import com.example.standardtask.utilities.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityVM : ViewModel() {

    val bannerImagesResponse = MutableLiveData<ScreenState<MainSliderImagesModel>>()
    val categoriesResponse = MutableLiveData<ScreenState<CategoriesModel>>()
    private val repository = Repository(RetrofitObject.retrofit)

    fun getBannerImages() {

        bannerImagesResponse.postValue(ScreenState.Loading(null))

            viewModelScope.launch(Dispatchers.IO) {

                try {

                    bannerImagesResponse.postValue(
                        ScreenState.Success(
                            repository.getBannerImages(
                                "en",
                                BodySent("ChIJ88rv8bI_WBQRkvVBLDeZQUg")
                            ).body()!!
                        )
                    )
                }catch (e: Exception) {
                    bannerImagesResponse.postValue(ScreenState.Error(e.message.toString(), null))
                }
            }

    }

    fun geCategories() {

        categoriesResponse.postValue(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {

            try {

                categoriesResponse.postValue(
                    ScreenState.Success(
                        repository.geCategories("en").body()!!
                    )
                )



            }catch (e: Exception) {
                categoriesResponse.postValue(ScreenState.Error(e.message.toString(), null))

            }
        }

    }

}