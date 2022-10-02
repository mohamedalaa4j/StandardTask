package com.example.standardtask.data.models.received


import com.google.gson.annotations.SerializedName

class CategoriesModel : ArrayList<CategoriesModel.CategoriesModelItem>(){

    data class CategoriesModelItem(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("name_en")
        val nameEn: String?,
        @SerializedName("percentage")
        val percentage: Int?,
        @SerializedName("photo")
        val photo: String?
    )
}