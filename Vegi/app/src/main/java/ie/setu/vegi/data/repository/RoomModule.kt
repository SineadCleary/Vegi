package ie.setu.vegi.data.repository

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ie.setu.vegi.data.room.AppDatabase
import ie.setu.vegi.data.room.ProductDAO
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context):
            AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "product_database")
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideProductDAO(appDatabase: AppDatabase):
            ProductDAO = appDatabase.getProductDAO()

    @Provides
    fun provideRoomRepository(productDAO: ProductDAO):
            RoomRepository = RoomRepository(productDAO)

}
