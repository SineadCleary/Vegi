package ie.setu.vegi.data.repository

import ie.setu.vegi.data.ProductModel
import ie.setu.vegi.data.room.ProductDAO
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RoomRepository @Inject
constructor(private val productDAO: ProductDAO) {
    fun getAll(): Flow<List<ProductModel>>
            = productDAO.getAll()

    fun get(id: Int) = productDAO.get(id)

    suspend fun insert(product: ProductModel)
    { productDAO.insert(product) }

    suspend fun update(product: ProductModel)
    { productDAO.update(product.id, product.vegStatus, product.name) }

    suspend fun delete(product: ProductModel)
    { productDAO.delete(product) }
}
