package ie.setu.vegi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import java.util.Date

@Entity
data class ProductModel(
    @DocumentId val _id: String = "N/A",
    @PrimaryKey val barcode: String = "",
    val name: String = "Name",
    val brand: String = "Brand",
    val imageUrl: String = "",
    val vegStatus: VegStatus = VegStatus.UNKNOWN,
    val dateAdded: Date = Date(),
    var email: String = "joe@bloggs.com",
)

val fakeProducts = List(30) { i ->
    ProductModel(
        barcode = "12345$i",
        name = "Example $i",
        brand = "Example brand",
        vegStatus = when (i % 4) {
            0 -> VegStatus.VEGAN
            1 -> VegStatus.VEGETARIAN
            2 -> VegStatus.NON_VEGETARIAN
            else -> VegStatus.UNKNOWN
        }
    )
}

enum class VegStatus {
    VEGAN,
    VEGETARIAN,
    NON_VEGETARIAN,
    UNKNOWN
}