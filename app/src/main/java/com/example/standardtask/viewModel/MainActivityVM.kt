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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _bannerImagesStateFlow =
        MutableStateFlow<ScreenState<MainSliderImagesModel>>(ScreenState.InitialValue(null))
    val bannerImagesStateFlow: StateFlow<ScreenState<MainSliderImagesModel>> = _bannerImagesStateFlow

    private val _categoriesStateFlow = MutableStateFlow<ScreenState<CategoriesModel>>(ScreenState.InitialValue(null))
    val categoriesStateFlow: StateFlow<ScreenState<CategoriesModel>> = _categoriesStateFlow

    private val _homePageComponentsStateFlow =
        MutableStateFlow<ScreenState<HomePageComponantsModel>>(ScreenState.InitialValue(null))
    val homePageComponentsStateFlow: StateFlow<ScreenState<HomePageComponantsModel>> = _homePageComponentsStateFlow

    fun getBannerImages() {

        viewModelScope.launch(Dispatchers.IO) {

            _bannerImagesStateFlow.emit(ScreenState.Loading(null))

            try {

                    _bannerImagesStateFlow.emit(
                        ScreenState.Success(
                            repository.getBannerImages(
                                Utilities.deviceLanguage(),
                                BodySent(Constants.GOOGLE_ID)
                            ).body()!!
                        )
                    )

            } catch (e: Exception) {
                _bannerImagesStateFlow.emit(ScreenState.Error(e.message.toString(), null))
            }
        }

    }

    fun geCategories() {

        viewModelScope.launch(Dispatchers.IO) {

            _categoriesStateFlow.emit(ScreenState.Loading(null))


            try {

                _categoriesStateFlow.emit(
                    ScreenState.Success(
                        repository.geCategories(Utilities.deviceLanguage()).body()!!
                    )
                )


            } catch (e: Exception) {
                _categoriesStateFlow.emit(ScreenState.Error(e.message.toString(), null))

            }
        }

    }

    fun geHomePageComponents() {

        viewModelScope.launch(Dispatchers.IO) {

            _homePageComponentsStateFlow.emit(ScreenState.Loading(null))

            try {

                _homePageComponentsStateFlow.emit(
                    ScreenState.Success(
                        repository.getHomePageComponents(Utilities.deviceLanguage(), BodySent(Constants.GOOGLE_ID))
                            .body()!!
                    )
                )


            } catch (e: Exception) {
                _homePageComponentsStateFlow.emit(ScreenState.Error(e.message.toString(), null))
                Log.e("error", e.message.toString())
            }
        }

    }

}