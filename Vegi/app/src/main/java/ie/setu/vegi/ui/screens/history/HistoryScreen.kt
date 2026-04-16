package ie.setu.vegi.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.vegi.R
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.models.fakeProducts
import ie.setu.vegi.ui.components.general.Centre
import ie.setu.vegi.ui.components.history.FilterChipRow
import ie.setu.vegi.ui.components.history.ProductCardList
import ie.setu.vegi.ui.theme.VegiTheme

@Composable
fun HistoryScreen(modifier: Modifier = Modifier,
                  onClickDetails: (String) -> Unit,
                  historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val products = historyViewModel.uiProducts.collectAsState().value
    if (products.isEmpty()){
        Centre(Modifier.fillMaxSize()) {
            Text(color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 34.sp,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.emptyList))
        }
    }
    else {
        Column {
            Column(
                modifier = modifier.padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            ) {
                ProductCardList(
                    products = products.reversed(),
                    onClickDetails = onClickDetails,
                    onDeleteProduct = {
                        product: ProductModel
                        -> historyViewModel.deleteProduct(product)
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HistoryScreenPreview() {
//    VegiTheme() {
//        PreviewHistoryScreen( modifier = Modifier,
//            products = fakeProducts.toMutableStateList()
////            products = emptyProducts.toMutableStateList()
//        )
//    }
//}

//@Composable
//fun PreviewHistoryScreen(modifier: Modifier = Modifier,
//                         products: SnapshotStateList<ProductModel>
//) {
//    Column {
//        Column(
//            modifier = modifier.padding(
//                start = 10.dp,
//                end = 10.dp
//            ),
//        ) {
//            FilterChipRow(
//                selectedFilters = setOf("Vegan", "Vegetarian"),
//                onFilterChanged = { _, _  -> }
//            )
//            ProductCardList(
//                products = products,
//                onDeleteProduct = {}
//            ) {}
//        }
//    }
//}

