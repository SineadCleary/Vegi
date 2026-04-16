package ie.setu.vegi.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ie.setu.vegi.data.models.ProductModel

@Database(entities = [ProductModel::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getProductDAO(): ProductDAO
}
