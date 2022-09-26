package com.example.standardtask.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standardtask.model.BodySent
import com.example.standardtask.model.MainSliderImagesModel
import com.example.standardtask.network.RetrofitObject
import com.example.standardtask.utilities.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityVM: ViewModel() {

    val postMutableLiveData = MutableLiveData<ScreenState<MainSliderImagesModel>>()

    fun getSliderImages() {

        postMutableLiveData.postValue(ScreenState.Loading(null))
        try {

            viewModelScope.launch(Dispatchers.IO) {
                val response = RetrofitObject.retrofit.getMainSliderImages("en", BodySent("ChIJ88rv8bI_WBQRkvVBLDeZQUg"))

                postMutableLiveData.postValue(ScreenState.Success(response.body()!!))

            }
        }catch (e: Exception){
            postMutableLiveData.postValue(ScreenState.Error(e.message.toString(), null))

        }

    }

}