package ie.setu.vegi.ui.components.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.models.VegStatus
import ie.setu.vegi.data.models.fakeProducts
import ie.setu.vegi.data.models.mapVegStatus
import ie.setu.vegi.ui.theme.VegiTheme
import java.text.DateFormat


@Composable
internal fun ProductCardList(
    products: List<ProductModel>,
    modifier: Modifier = Modifier,
    onDeleteProduct: (ProductModel) -> Unit,
    onClickDetails: (String) -> Unit
) {
    var filters by remember { mutableStateOf(setOf<VegStatus>()) }
    LazyColumn {
        item {
            FilterChipRow(
                selectedFilters = filters,
                onFilterChanged = { filter, isSelected ->
                    filters = if (isSelected) filters + filter else filters - filter
                }
            )
        }
        val filteredProducts =
            if (filters.isEmpty()) {
                products
            } else {
                products.filter { product ->
                    filters.contains(product.vegStatus)
                }
            }
        items(
            items = filteredProducts,
            key = { product -> product._id }
        ) { product ->
            ProductCard(
                vegStaus = when (product.vegStatus) {
                    VegStatus.VEGAN -> "Vegan"
                    VegStatus.VEGETARIAN -> "Vegetarian"
                    VegStatus.NON_VEGETARIAN -> "Non-vegetarian"
                    else -> "Unknown"
                },
                name = product.name,
                brand = product.brand,
                imagePath = product.imageUrl,
                dateCreated = DateFormat.getDateTimeInstance().format(product.dateAdded),
                onClickDelete = { onDeleteProduct(product) },
                onClickDetails = { onClickDetails(product._id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardListPreview() {
    VegiTheme {
        ProductCardList(
            fakeProducts.toMutableStateList(),
            modifier = Modifier,
            onDeleteProduct = {},
        ) { }
    }
}
