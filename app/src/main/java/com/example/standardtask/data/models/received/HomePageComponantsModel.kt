package com.example.standardtask.data.models.received


import com.google.gson.annotations.SerializedName

data class HomePageComponantsModel(
    @SerializedName("GetFreeDliveryBranches")
    val getFreeDliveryBranches: GetFreeDliveryBranches?,
    @SerializedName("getMostOrderedBranch")
    val getMostOrderedBranch: GetMostOrderedBranch?,
    @SerializedName("GetNearestBranche")
    val getNearestBranche: GetNearestBranche?,
    @SerializedName("GetPercentageForVendors")
    val getPercentageForVendors: GetPercentageForVendors?,
    @SerializedName("lastoffers")
    val lastoffers: Lastoffers?,
    @SerializedName("MostSellItems")
    val mostSellItems: MostSellItems?
) {
    data class GetFreeDliveryBranches(
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class Data(
            @SerializedName("cover")
            val cover: String?,
            @SerializedName("cuisines")
            val cuisines: List<Cuisine?>?,
            @SerializedName("delivery_cost")
            val deliveryCost: Int?,
            @SerializedName("delivery_time")
            val deliveryTime: Int?,
            @SerializedName("description")
            val description: Any?,
            @SerializedName("IsOpen")
            val isOpen: String?,
            @SerializedName("logo")
            val logo: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("rate")
            val rate: Any?,
            @SerializedName("RestauranthId")
            val restauranthId: Int?
        ) {
            data class Cuisine(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("name")
                val name: String?
            )
        }
    }

    data class GetMostOrderedBranch(
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class Data(
            @SerializedName("branches")
            val branches: Branches?,
            @SerializedName("cover")
            val cover: String?,
            @SerializedName("delivery_cost")
            val deliveryCost: Int?,
            @SerializedName("delivery_time")
            val deliveryTime: Int?,
            @SerializedName("IsOpen")
            val isOpen: String?,
            @SerializedName("logo")
            val logo: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("ordersnumber")
            val ordersnumber: Int?,
            @SerializedName("RestauranthId")
            val restauranthId: Int?,
            @SerializedName("total")
            val total: Double?
        ) {
            data class Branches(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("restaurant")
                val restaurant: Restaurant?
            ) {
                data class Restaurant(
                    @SerializedName("cover")
                    val cover: String?,
                    @SerializedName("cuisines")
                    val cuisines: List<Cuisine?>?,
                    @SerializedName("description")
                    val description: Any?,
                    @SerializedName("logo")
                    val logo: String?,
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("RestauranthId")
                    val restauranthId: Int?
                ) {
                    data class Cuisine(
                        @SerializedName("id")
                        val id: Int?,
                        @SerializedName("name")
                        val name: String?
                    )
                }
            }
        }
    }

    data class GetNearestBranche(
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class Data(
            @SerializedName("cover")
            val cover: String?,
            @SerializedName("cuisines")
            val cuisines: List<Cuisine?>?,
            @SerializedName("delivery_cost")
            val deliveryCost: Int?,
            @SerializedName("delivery_time")
            val deliveryTime: Int?,
            @SerializedName("description")
            val description: Any?,
            @SerializedName("IsOpen")
            val isOpen: String?,
            @SerializedName("logo")
            val logo: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("rate")
            val rate: Any?,
            @SerializedName("RestauranthId")
            val restauranthId: Int?
        ) {
            data class Cuisine(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("name")
                val name: String?
            )
        }
    }

    data class GetPercentageForVendors(
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class Data(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("restaurants")
            val restaurants: Restaurants?
        ) {
            data class Restaurants(
                @SerializedName("cover")
                val cover: String?,
                @SerializedName("cuisines")
                val cuisines: List<Cuisine?>?,
                @SerializedName("delivery_cost")
                val deliveryCost: Int?,
                @SerializedName("delivery_time")
                val deliveryTime: Int?,
                @SerializedName("IsOpen")
                val isOpen: String?,
                @SerializedName("logo")
                val logo: String?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("rate")
                val rate: Any?,
                @SerializedName("RestauranthId")
                val restauranthId: Int?
            ) {
                data class Cuisine(
                    @SerializedName("id")
                    val id: Int?,
                    @SerializedName("name")
                    val name: String?
                )
            }
        }
    }

    data class Lastoffers(
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class Data(
            @SerializedName("cover")
            val cover: String?,
            @SerializedName("cuisines")
            val cuisines: List<Cuisine?>?,
            @SerializedName("delivery_cost")
            val deliveryCost: Int?,
            @SerializedName("delivery_time")
            val deliveryTime: Int?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("IsOpen")
            val isOpen: String?,
            @SerializedName("logo")
            val logo: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("rate")
            val rate: Any?,
            @SerializedName("RestauranthId")
            val restauranthId: Int?
        ) {
            data class Cuisine(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("name")
                val name: String?
            )
        }
    }

    data class MostSellItems(
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class Data(
            @SerializedName("delivery_cost")
            val deliveryCost: Int?,
            @SerializedName("delivery_time")
            val deliveryTime: Int?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("IsOpen")
            val isOpen: String?,
            @SerializedName("itemamount")
            val itemamount: String?,
            @SerializedName("menu_categories_items")
            val menuCategoriesItems: MenuCategoriesItems?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("price")
            val price: Int?,
            @SerializedName("restaurantsId")
            val restaurantsId: Int?
        ) {
            data class MenuCategoriesItems(
                @SerializedName("active")
                val active: Int?,
                @SerializedName("created")
                val created: String?,
                @SerializedName("descriptions")
                val descriptions: String?,
                @SerializedName("descriptions_en")
                val descriptionsEn: String?,
                @SerializedName("id")
                val id: Int?,
                @SerializedName("menu_categories_id")
                val menuCategoriesId: Int?,
                @SerializedName("modified")
                val modified: String?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("name_en")
                val nameEn: String?,
                @SerializedName("photo")
                val photo: String?,
                @SerializedName("price")
                val price: Int?
            )
        }
    }
}