package com.example.standardtask.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.standardtask.adapters.RvAdapterCategories
import com.example.standardtask.databinding.ActivityMainBinding
import com.example.standardtask.model.models.received.CategoriesModel
import com.example.standardtask.model.models.received.MainSliderImagesModel
import com.example.standardtask.utilities.ScreenState
import com.example.standardtask.utilities.Utilities
import com.example.standardtask.viewModel.MainActivityVM

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private val viewModel: MainActivityVM by lazy {
        ViewModelProvider(this)[MainActivityVM::class.java]
    }

    private val imageList = ArrayList<SlideModel>()

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

                    for (i in response) {
                        imageList.add(SlideModel("https://satatechnologygroup.net:3301/" + i.sliders.photo))
                    }

                    binding?.imageSliderShow?.setImageList(imageList, ScaleTypes.FIT)
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

                    setupRV(response)
                }
                Utilities.cancelProgressDialog()


            }


            is ScreenState.Error -> {
                Toast.makeText(this, screenState.message, Toast.LENGTH_SHORT).show()
                Log.e("error", "null")
                Utilities.cancelProgressDialog()
            }
        }
    }

    private fun setupRV(data: CategoriesModel) {
        binding?.rvType?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // adapter
        val adapter = RvAdapterCategories(data){}
        binding?.rvType?.adapter = adapter


    }

}