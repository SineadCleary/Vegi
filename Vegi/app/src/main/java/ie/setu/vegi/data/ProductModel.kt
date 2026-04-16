package ie.setu.vegi.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ProductModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var vegStatus: String = "N/A",
    var name: String = "Product Name",
    var imagePath: String = "",
    val dateAdded: Date = Date()
)

val fakeProducts = List(30) { i ->
    ProductModel(id = (12345 + i),
        when (i % 3) {
            0 -> "Vegan"
            1 -> "Vegetarian"
            else -> "Unknown"
        },
        "Example",
        imagePath = "",
        Date()
        )
}

val emptyProducts = List(0) { i ->
    ProductModel(id = 0, vegStatus = "", "", imagePath = "", Date())
}