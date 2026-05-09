package ie.setu.vegi.firebase.services

import ie.setu.vegi.data.models.ProductModel
import kotlinx.coroutines.flow.Flow

typealias Product = ProductModel
typealias Products = Flow<List<Product>>

interface FirestoreService {

    suspend fun getAll(email: String) : Products
    suspend fun get(email: String, productId: String) : Product?
    suspend fun getByBarcode(email: String, barcode: String) : Product?
    suspend fun insert(email: String, product: Product)
    suspend fun update(email: String, product: Product)
    suspend fun delete(email: String, productId: String)
    suspend fun deleteUserHistory(email: String)
}
