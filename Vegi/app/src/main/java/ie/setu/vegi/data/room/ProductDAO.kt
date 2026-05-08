package ie.setu.vegi.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ie.setu.vegi.data.models.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {
    @Query("SELECT * FROM productmodel")
    fun getAll(): Flow<List<ProductModel>>

    @Query("SELECT * FROM productmodel WHERE barcode=:barcode")
    fun get(barcode: String): Flow<ProductModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ProductModel)

    @Query("UPDATE productmodel SET vegStatus=:vegStatus, name=:name WHERE barcode = :barcode")
    suspend fun update(barcode: String, vegStatus:String, name:String)

    @Delete
    suspend fun delete(product: ProductModel)
}
