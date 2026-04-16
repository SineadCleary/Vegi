package ie.setu.vegi.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ie.setu.vegi.data.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {
    @Query("SELECT * FROM productmodel")
    fun getAll(): Flow<List<ProductModel>>

    @Query("SELECT * FROM productmodel WHERE id=:id")
    fun get(id: Int): Flow<ProductModel>

    @Insert
    suspend fun insert(product: ProductModel)

    @Query("UPDATE productmodel SET vegStatus=:vegStatus, name=:name WHERE id = :id")
    suspend fun update(id: Int, vegStatus:String, name:String)

    @Delete
    suspend fun delete(product: ProductModel)
}
