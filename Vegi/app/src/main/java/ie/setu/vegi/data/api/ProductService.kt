package ie.setu.vegi.data.api

import ie.setu.vegi.data.models.OpenFoodFactsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET(ServiceEndPoints.PRODUCT_ENDPOINT + "/{barcode}.json")
    suspend fun get(@Path("barcode") barcode: String): Response<OpenFoodFactsResponseDto>
}