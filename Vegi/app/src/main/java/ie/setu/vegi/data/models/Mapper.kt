package ie.setu.vegi.data.models

class Mapper {
    fun OpenFoodFactsModel.toEntity(): ProductModel {
        return ProductModel(
            barcode = barcode,
            name = name,
            brand = brands,
            imageUrl = imageUrl,
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
}