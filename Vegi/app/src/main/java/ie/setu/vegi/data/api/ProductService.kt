package ie.setu.vegi.data.api

import ie.setu.vegi.data.models.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET(ServiceEndPoints.PRODUCT_ENDPOINT + "/{barcode}")
    suspend fun get(@Path("barcode") barcode: String): Response<List<ProductModel>>
}