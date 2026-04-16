package ie.setu.vegi.data.models

import com.google.gson.annotations.SerializedName

data class OpenFoodFactsResponseDto(
    val code: String,
    val product: OpenFoodFactsModel?
)

data class OpenFoodFactsModel(
    @SerializedName("product_name")
    val name: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    val brands: String?,
    @SerializedName("ingredients_analysis_tags")
    val tags: List<String>? = null
)