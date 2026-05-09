package ie.setu.vegi.ui.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.models.VegStatus
import ie.setu.vegi.ui.components.general.ScanFloatingActionButton
import ie.setu.vegi.ui.components.general.ShowError
import ie.setu.vegi.ui.components.general.ShowLoader
import ie.setu.vegi.ui.components.history.FilterChipRow
import ie.setu.vegi.ui.components.history.ProductCardList
import kotlin.collections.plus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier,
                 onClickDetails: (String) -> Unit,
                 onClickScan: () -> Unit,
                 searchViewModel: SearchViewModel = hiltViewModel()
) {
    val products = searchViewModel.uiProducts.collectAsState().value
    val isError = searchViewModel.iserror.value
    val error = searchViewModel.error.value
    val isLoading = searchViewModel.isloading.value
    var filters by remember { mutableStateOf(setOf<VegStatus>()) }

    var searchQuery by remember { mutableStateOf("") }
    val searchedItems = products
        .filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
            it.brand.contains(searchQuery, ignoreCase = true) ||
            it.vegStatus.toString().contains(searchQuery, ignoreCase = true)
        }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier.padding(
                start = 10.dp,
                end = 10.dp
            ),
        ) {
            if (!isError) {
                FilterChipRow(
                    selectedFilters = filters,
                    onFilterChanged = { filter, isSelected ->
                        filters = if (isSelected) filters + filter else filters - filter
                    }
                )
            }
            DockedSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { },
                active = false,
                onActiveChange = {},

                modifier = modifier
                    .padding(start = 5.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(),

                placeholder = { Text("Search scanned products") },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                ),
                tonalElevation = 0.dp,
            ) { }

            if(isLoading) ShowLoader("Loading products...")

            if (!isError && searchQuery != "") {
                ProductCardList(
                    products = searchedItems,
                    onClickDetails = onClickDetails,
                    filters = filters,
                    onDeleteProduct = { product: ProductModel
                        ->
                        searchViewModel.deleteProduct(product)
                    }
                )
            }
            if (isError) {
                ShowError(headline = error.message!! + " error...",
                    subtitle = error.toString(),
                    onClick = { searchViewModel.getProducts() })
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

