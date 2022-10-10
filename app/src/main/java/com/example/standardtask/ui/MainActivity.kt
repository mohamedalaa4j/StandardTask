package com.example.standardtask.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.standardtask.adapters.RvAdapterLatestOffers
import com.example.standardtask.adapters.RvAdapterBestSellingRestaurants
import com.example.standardtask.adapters.RvAdapterCategories
import com.example.standardtask.adapters.RvAdapterMostOrderedItems
import com.example.standardtask.data.models.received.CategoriesModel
import com.example.standardtask.data.models.received.HomePageComponantsModel
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

        viewModel.getBannerImages()

        binding?.btn?.setOnClickListener {
            viewModel.getBannerImages()

        }

        lifecycleScope.launchWhenStarted {
            viewModel.bannerImagesStateFlow.collect {
                bannerImagesStateFlow(it)

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.categoriesStateFlow.collect {
                categoriesStateFlow(it)

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.homePageComponentsStateFlow.collect {
                homePageComponentsStateFlow(it)
            }
        }


    }//onCreate/////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun bannerImagesStateFlow(screenState: ScreenState<MainSliderImagesModel>) {
        when (screenState) {

            is ScreenState.InitialValue -> {
            }

            is ScreenState.Loading -> {
                Utilities.showProgressDialog(this)
            }

            is ScreenState.Success -> {
                if (screenState.data != null) {

                    var adsPositionTop = -1
                    var adsPositionMiddle = -1
                    var adsPositionBottom = -1
                    for (i in 0 until screenState.data.size) {
                        if (screenState.data[i].name == "الصفحة الرئيسية الجزء العلوي") {
                            adsPositionTop = i
                        }

                        if (screenState.data[i].name == "الصفحة الرئيسية الجزء الوسط") {
                            adsPositionMiddle = i
                        }

                        if (screenState.data[i].name == "الصفحة الرئيسية الجزء السفلي") {
                            adsPositionBottom = i
                        }

                    }

                    val topAds = screenState.data[adsPositionTop].adsSpacesprice
                    val middleAds = screenState.data[adsPositionMiddle].adsSpacesprice
                    val bottomAds = screenState.data[adsPositionBottom].adsSpacesprice

                    initializeBannerImagesSliderTop(topAds)
                    initializeBannerImagesSliderMiddle(middleAds)
                    initializeBannerImagesSliderBottom(bottomAds)

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

    private fun categoriesStateFlow(screenState: ScreenState<CategoriesModel>) {
        when (screenState) {

            is ScreenState.InitialValue -> {
            }

            is ScreenState.Loading -> {
                Utilities.showProgressDialog(this)

            }

            is ScreenState.Success -> {
                if (screenState.data != null) {
                    val response = screenState.data

                    initializeCategoriesRV(response)
                }

                Utilities.cancelProgressDialog()

                    viewModel.geHomePageComponents()

            }


            is ScreenState.Error -> {
                Toast.makeText(this, screenState.message, Toast.LENGTH_SHORT).show()
                Utilities.cancelProgressDialog()
            }
        }
    }

    private fun homePageComponentsStateFlow(screenState: ScreenState<HomePageComponantsModel>) {
        when (screenState) {

            is ScreenState.InitialValue -> {
            }

            is ScreenState.Loading -> {
                Utilities.showProgressDialog(this)
            }

            is ScreenState.Success -> {
                if (screenState.data != null) {
                    val getMostOrderedBranch = screenState.data.getMostOrderedBranch?.data!!
                    val mostSellItems = screenState.data.mostSellItems?.data!!
                    val latestOffers = screenState.data.lastoffers?.data!!

                    initializeBestSellingRestaurantsRV(getMostOrderedBranch)
                    initializeMostOrderedItemsRV(mostSellItems)
                    initializeLatestOffersRV(latestOffers)
                }
                Utilities.cancelProgressDialog()


            }


            is ScreenState.Error -> {
                Toast.makeText(this, screenState.message, Toast.LENGTH_SHORT).show()
                Utilities.cancelProgressDialog()

            }
        }
    }

    private fun initializeBannerImagesSliderTop(response: List<MainSliderImagesModel.MainSliderImagesModelItem.AdsSpacesprice>) {

        val imageList = ArrayList<SlideModel>()

        for (i in response) {
            imageList.add(SlideModel(Constants.IMAGES_BASE_URL + i.sliders.photo))
        }

        binding?.imageSliderShow?.setImageList(imageList, ScaleTypes.FIT)


    }

    private fun initializeBannerImagesSliderMiddle(response: List<MainSliderImagesModel.MainSliderImagesModelItem.AdsSpacesprice>) {

        val imageList = ArrayList<SlideModel>()

        for (i in response) {
            imageList.add(SlideModel(Constants.IMAGES_BASE_URL + i.sliders.photo))
        }

        binding?.imageSliderShowMiddle?.setImageList(imageList, ScaleTypes.FIT)


    }

    private fun initializeBannerImagesSliderBottom(response: List<MainSliderImagesModel.MainSliderImagesModelItem.AdsSpacesprice>) {

        val imageList = ArrayList<SlideModel>()

        for (i in response) {
            imageList.add(SlideModel(Constants.IMAGES_BASE_URL + i.sliders.photo))
        }

        binding?.imageSliderShowBottom?.setImageList(imageList, ScaleTypes.FIT)


    }

    private fun initializeCategoriesRV(data: CategoriesModel) {
        binding?.rvCategories?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // adapter
        val adapter = RvAdapterCategories(data) {}
        binding?.rvCategories?.adapter = adapter


    }

    private fun initializeBestSellingRestaurantsRV(data: List<HomePageComponantsModel.GetMostOrderedBranch.Data?>) {
        binding?.rvBestSellingRestaurants?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // adapter
        val adapter = RvAdapterBestSellingRestaurants(data) {}
        binding?.rvBestSellingRestaurants?.adapter = adapter


    }

    private fun initializeMostOrderedItemsRV(data: List<HomePageComponantsModel.MostSellItems.Data?>) {
        binding?.rvMostOrderedItems?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // adapter
        val adapter = RvAdapterMostOrderedItems(data) {}
        binding?.rvMostOrderedItems?.adapter = adapter


    }

    private fun initializeLatestOffersRV(data: List<HomePageComponantsModel.Lastoffers.Data?>) {
        binding?.rvLatestOffers?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // adapter
        val adapter = RvAdapterLatestOffers(data) {}
        binding?.rvLatestOffers?.adapter = adapter


    }


}