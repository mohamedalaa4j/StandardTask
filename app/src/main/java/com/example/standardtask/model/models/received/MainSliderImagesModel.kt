package com.example.standardtask.model.models.received


import com.google.gson.annotations.SerializedName

class MainSliderImagesModel : ArrayList<MainSliderImagesModel.MainSliderImagesModelItem>(){
    data class MainSliderImagesModelItem(
        @SerializedName("AdsSpacesprice")
        val adsSpacesprice: List<AdsSpacesprice>,
        @SerializedName("created")
        val created: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    ) {
        data class AdsSpacesprice(
            @SerializedName("id")
            val id: Int,
            @SerializedName("positions")
            val positions: Int,
            @SerializedName("sliders")
            val sliders: Sliders
        ) {
            data class Sliders(
                @SerializedName("ads_position_price_id")
                val adsPositionPriceId: Int,
                @SerializedName("approval")
                val approval: Int,
                @SerializedName("created")
                val created: String,
                @SerializedName("day_number")
                val dayNumber: Int,
                @SerializedName("department_id")
                val departmentId: Int,
                @SerializedName("department_message")
                val departmentMessage: String,
                @SerializedName("end_date")
                val endDate: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("item_id")
                val itemId: Int,
                @SerializedName("modified")
                val modified: String,
                @SerializedName("photo")
                val photo: String,
                @SerializedName("publish")
                val publish: Int,
                @SerializedName("restaurant_id")
                val restaurantId: Int,
                @SerializedName("slider_seconds")
                val sliderSeconds: Int,
                @SerializedName("sort")
                val sort: Int,
                @SerializedName("start_date")
                val startDate: String,
                @SerializedName("total_cost")
                val totalCost: Int
            )
        }
    }
}