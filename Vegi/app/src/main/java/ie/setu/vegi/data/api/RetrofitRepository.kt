package ie.setu.vegi.data.api

import ie.setu.vegi.data.models.OpenFoodFactsModel
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.models.mapVegStatus
import ie.setu.vegi.data.models.toProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject
    constructor(private val serviceApi: ProductService) {

        suspend fun get(barcode: String): ProductModel? {
            return withContext(Dispatchers.IO) {
                val response = serviceApi.get(barcode)
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.product?.toProductModel(data.code)
                } else {
                    null
                }
            }
        }
}