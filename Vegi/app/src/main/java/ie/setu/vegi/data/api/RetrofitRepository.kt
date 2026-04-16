package ie.setu.vegi.data.api

import ie.setu.vegi.data.models.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject
    constructor(private val serviceApi: ProductService) {

        suspend fun get(barcode: String): List<ProductModel>{
            return withContext(Dispatchers.IO) {
                val product = serviceApi.get(barcode)
                product.body() ?: emptyList()
            }
        }

}