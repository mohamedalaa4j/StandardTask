package com.example.standardtask.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standardtask.model.Repository
import com.example.standardtask.model.models.BodySent
import com.example.standardtask.model.models.MainSliderImagesModel
import com.example.standardtask.network.RetrofitObject
import com.example.standardtask.utilities.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityVM : ViewModel() {

    val bannerImagesResponse = MutableLiveData<ScreenState<MainSliderImagesModel>>()

    fun getBannerImages() {

        bannerImagesResponse.postValue(ScreenState.Loading(null))

            viewModelScope.launch(Dispatchers.IO) {

                val repository = Repository(RetrofitObject.retrofit)

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

}