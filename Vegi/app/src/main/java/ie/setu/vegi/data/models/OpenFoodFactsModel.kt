package ie.setu.vegi.data.models

import com.google.gson.annotations.SerializedName

data class OpenFoodFactsModel(
    @SerializedName("code")
    val barcode: String,
    @SerializedName("product_name")
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val brands: String,
    @SerializedName("ingredients_analysis_tags")
    val tags: List<String>? = null
)