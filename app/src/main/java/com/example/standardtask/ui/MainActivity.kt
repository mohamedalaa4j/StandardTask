package com.example.standardtask.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.standardtask.adapters.RvAdapterCategories
import com.example.standardtask.data.models.received.CategoriesModel
import com.example.standardtask.data.models.received.MainSliderImagesModel
import com.example.standardtask.databinding.ActivityMainBinding
import com.example.standardtask.utilities.Constants
import com.example.standardtask.utilities.ScreenState
import com.example.standardtask.utilities.Utilities
import com.example.standardtask.viewModel.MainActivityVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private val viewModel: MainActivityVM by lazy {
        ViewModelProvider(this)[MainActivityVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btn?.setOnClickListener {
            viewModel.getBannerImages()
        }

        viewModel.bannerImagesResponse.observe(this) {
            setupBannerImagesSlider(it)
        }

        viewModel.categoriesResponse.observe(this) {
            setupCategoriesRV(it)
        }


    }//onCreate/////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupBannerImagesSlider(screenState: ScreenState<MainSliderImagesModel>) {
        when (screenState) {

            is ScreenState.Loading -> {
                Utilities.showProgressDialog(this)
            }

            is ScreenState.Success -> {
                if (screenState.data != null) {
                    val response = screenState.data[0].adsSpacesprice

                    initializeBannerImagesSlider(response)

                }
                Utilities.cancelProgressDialog()

                viewModel.geCategories()

            }

            is ScreenState.Error -> {
                Toast.makeText(this, screenState.message, Toast.LENGTH_SHORT).show()
                Utilities.cancelProgressDialog()
            }
        }
    }

    private fun setupCategoriesRV(screenState: ScreenState<CategoriesModel>) {
        when (screenState) {

            is ScreenState.Loading -> {
                Utilities.showProgressDialog(this)
            }

            is ScreenState.Success -> {
                if (screenState.data != null) {
                    val response = screenState.data

                    initializeCategoriesRV(response)
                }
                Utilities.cancelProgressDialog()


            }


            is ScreenState.Error -> {
                Toast.makeText(this, screenState.message, Toast.LENGTH_SHORT).show()
                Utilities.cancelProgressDialog()
            }
        }
    }

    private fun initializeCategoriesRV(data: CategoriesModel) {
        binding?.rvType?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // adapter
        val adapter = RvAdapterCategories(data) {}
        binding?.rvType?.adapter = adapter


    }

    private fun initializeBannerImagesSlider(response: List<MainSliderImagesModel.MainSliderImagesModelItem.AdsSpacesprice>) {

        val imageList = ArrayList<SlideModel>()

        for (i in response) {
            imageList.add(SlideModel(Constants.IMAGES_BASE_URL + i.sliders.photo))
        }

        binding?.imageSliderShow?.setImageList(imageList, ScaleTypes.FIT)


    }

}