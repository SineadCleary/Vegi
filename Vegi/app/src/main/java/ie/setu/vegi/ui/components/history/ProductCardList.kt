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
import ie.setu.vegi.data.ProductModel
import ie.setu.vegi.data.fakeProducts
import ie.setu.vegi.ui.theme.VegiTheme
import java.text.DateFormat
import kotlin.text.contains


@Composable
internal fun ProductCardList(
    products: List<ProductModel>,
    modifier: Modifier = Modifier,
    onDeleteProduct: (ProductModel) -> Unit,
    onClickDetails: (Int) -> Unit
) {
    var filters by remember { mutableStateOf(setOf<String>()) }
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
            key = { product -> product.id }
        ) { product ->
            ProductCard(
                vegStaus = product.vegStatus,
                name = product.name,
                imagePath = product.imagePath,
                dateCreated = DateFormat.getDateTimeInstance().format(product.dateAdded),
                onClickDelete = { onDeleteProduct(product) },
                onClickDetails = { onClickDetails(product.id) }
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
