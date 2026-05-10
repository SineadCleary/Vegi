package ie.setu.vegi.ui.screens.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.vegi.R
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.models.VegStatus
import ie.setu.vegi.ui.components.general.Centre
import ie.setu.vegi.ui.components.general.ScanFloatingActionButton
import ie.setu.vegi.ui.components.general.ShowError
import ie.setu.vegi.ui.components.general.ShowLoader
import ie.setu.vegi.ui.components.history.FilterChipRow
import ie.setu.vegi.ui.components.history.ProductCardList
import kotlin.collections.plus

@Composable
fun HistoryScreen(modifier: Modifier = Modifier,
                  onClickDetails: (String) -> Unit,
                  onClickScan: () -> Unit,
                  historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val products = historyViewModel.uiProducts.collectAsState().value
    val isError = historyViewModel.iserror.value
    val error = historyViewModel.error.value
    val isLoading = historyViewModel.isloading.value
    var filters by remember { mutableStateOf(setOf<VegStatus>()) }


    Box(modifier = Modifier.fillMaxSize()) {
        if (products.isEmpty() && !isError){
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
            Column(
                modifier = modifier.padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            ) {
                if(isLoading) ShowLoader("Loading products...")
                if (!isError) {
                    FilterChipRow(
                        selectedFilters = filters,
                        onFilterChanged = { filter, isSelected ->
                            filters = if (isSelected) filters + filter else filters - filter
                        }
                    )
                    ProductCardList(
                        products = products,
                        onClickDetails = onClickDetails,
                        filters = filters,
                        onDeleteProduct = { product: ProductModel
                            ->
                            historyViewModel.deleteProduct(product)
                        }
                    )
                }
                if (isError) {
                    ShowError(headline = error.message!! + " error...",
                        subtitle = error.toString(),
                        onClick = { historyViewModel.getProducts() })
                }
            }
        }
        ScanFloatingActionButton(
            onClick = onClickScan,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}


