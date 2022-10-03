package com.example.standardtask.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standardtask.data.Repository
import com.example.standardtask.data.models.received.CategoriesModel
import com.example.standardtask.data.models.received.HomePageComponantsModel
import com.example.standardtask.data.models.received.MainSliderImagesModel
import com.example.standardtask.data.models.send.BodySent
import com.example.standardtask.utilities.Constants
import com.example.standardtask.utilities.ScreenState
import com.example.standardtask.utilities.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val repository: Repository) : ViewModel() {

//    val bannerImagesResponse = MutableLiveData<ScreenState<MainSliderImagesModel>>()
    val bannerImagesStateFlow = MutableStateFlow<ScreenState<MainSliderImagesModel>>(ScreenState.InitialValue(null))

//    val categoriesResponse = MutableLiveData<ScreenState<CategoriesModel>>()
    val categoriesStateFlow = MutableStateFlow<ScreenState<CategoriesModel>>(ScreenState.InitialValue(null))

//    val homePageComponentsResponse = MutableLiveData<ScreenState<HomePageComponantsModel>>()
    val homePageComponentsStateFlow = MutableStateFlow<ScreenState<HomePageComponantsModel>>(ScreenState.InitialValue(null))

    suspend fun getBannerImages() {

        bannerImagesStateFlow.emit(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {

            try {

                bannerImagesStateFlow.emit(
                    ScreenState.Success(
                        repository.getBannerImages(
                            Utilities.deviceLanguage(),
                            BodySent(Constants.GOOGLE_ID)
                        ).body()!!
                    )
                )
            } catch (e: Exception) {
                bannerImagesStateFlow.emit(ScreenState.Error(e.message.toString(), null))
            }
        }

    }

    suspend fun geCategories() {

        categoriesStateFlow.emit(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {

            try {

                categoriesStateFlow.emit(
                    ScreenState.Success(
                        repository.geCategories(Utilities.deviceLanguage()).body()!!
                    )
                )


            } catch (e: Exception) {
                categoriesStateFlow.emit(ScreenState.Error(e.message.toString(), null))

            }
        }

    }

    suspend fun geHomePageComponents() {

        homePageComponentsStateFlow.emit(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {

            try {

                homePageComponentsStateFlow.emit(
                    ScreenState.Success(
                        repository.getHomePageComponents(Utilities.deviceLanguage(),BodySent(Constants.GOOGLE_ID)).body()!!
                    )
                )


            } catch (e: Exception) {
                homePageComponentsStateFlow.emit(ScreenState.Error(e.message.toString(), null))
                Log.e("error",e.message.toString())
            }
        }

    }

}