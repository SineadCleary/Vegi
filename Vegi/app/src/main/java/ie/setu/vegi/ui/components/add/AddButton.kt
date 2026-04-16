package ie.setu.vegi.ui.components.add

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.vegi.R
import ie.setu.vegi.data.ProductModel
import ie.setu.vegi.data.fakeProducts
import ie.setu.vegi.ui.screens.add.AddViewModel
import ie.setu.vegi.ui.screens.history.HistoryViewModel
import ie.setu.vegi.ui.theme.VegiTheme
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.collections.toList

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    product: ProductModel,
    addViewModel: AddViewModel = hiltViewModel(),
    historyViewModel: HistoryViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
) {
    val products = historyViewModel.uiProducts.collectAsState().value
    val scope = rememberCoroutineScope()
    Row {
        Button(
            onClick = {
                addViewModel.insert(product)
                Timber.i("Product info : $product")
                Timber.i("Product list info : ${products.toList()}")
                // Snackbar
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Product added",
                        actionLabel = "Dismiss",
                        duration = SnackbarDuration.Short
                    )
                }
            },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
            Spacer(modifier.width(width = 4.dp))
            Text(
                text = stringResource(R.string.addButton),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPreview() {
    VegiTheme {
        PreviewAddButton(
            Modifier,
            products = fakeProducts.toMutableStateList()
        )
    }
}

@Composable
fun PreviewAddButton(modifier: Modifier = Modifier,
                     products: SnapshotStateList<ProductModel>
) {
    Row {
        Button(
            onClick = { },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
            Spacer(modifier.width(width = 4.dp))
            Text(
                text = stringResource(R.string.addButton),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}
