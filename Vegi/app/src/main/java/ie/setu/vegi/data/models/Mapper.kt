package ie.setu.vegi.data.models

fun OpenFoodFactsModel.toProductModel(barcode: String): ProductModel {
    return ProductModel(
        barcode = barcode,
        name = name ?: "Unknown",
        brand = brands ?: "Unknown",
        imageUrl = imageUrl ?: "",
        vegStatus = mapVegStatus(tags)
    )
}

fun mapVegStatus(tags: List<String>?): VegStatus {
    return when {
        tags?.contains("en:vegan") == true -> VegStatus.VEGAN
        tags?.contains("en:vegetarian") == true -> VegStatus.VEGETARIAN
        tags?.contains("en:non-vegan") == true -> VegStatus.NON_VEGETARIAN
        else -> VegStatus.UNKNOWN
    }
}