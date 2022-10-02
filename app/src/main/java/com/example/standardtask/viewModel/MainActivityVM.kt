package com.example.standardtask.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standardtask.data.Repository
import com.example.standardtask.data.models.received.CategoriesModel
import com.example.standardtask.data.models.received.MainSliderImagesModel
import com.example.standardtask.data.models.send.BodySent
import com.example.standardtask.utilities.Constants
import com.example.standardtask.utilities.ScreenState
import com.example.standardtask.utilities.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val repository: Repository) : ViewModel() {

    val bannerImagesResponse = MutableLiveData<ScreenState<MainSliderImagesModel>>()
    val categoriesResponse = MutableLiveData<ScreenState<CategoriesModel>>()

    fun getBannerImages() {

        bannerImagesResponse.postValue(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {

            try {

                bannerImagesResponse.postValue(
                    ScreenState.Success(
                        repository.getBannerImages(
                            Utilities.deviceLanguage(),
                            BodySent(Constants.GOOGLE_ID)
                        ).body()!!
                    )
                )
            } catch (e: Exception) {
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
                        repository.geCategories(Utilities.deviceLanguage()).body()!!
                    )
                )


            } catch (e: Exception) {
                categoriesResponse.postValue(ScreenState.Error(e.message.toString(), null))

            }
        }

    }

}