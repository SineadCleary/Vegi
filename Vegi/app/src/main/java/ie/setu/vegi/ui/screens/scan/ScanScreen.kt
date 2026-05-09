package ie.setu.vegi.ui.screens.scan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.models.VegStatus
import ie.setu.vegi.ui.components.camera.CameraPermissionScreen
import ie.setu.vegi.ui.components.camera.CameraPreview
import ie.setu.vegi.ui.components.general.ShowError
import ie.setu.vegi.ui.components.general.ShowLoader
import ie.setu.vegi.ui.components.history.ProductCard
import timber.log.Timber
import java.util.Date

@Composable
fun ScanScreen(modifier: Modifier = Modifier,
               scanViewModel: ScanViewModel = hiltViewModel()) {

    var lastCode by remember { mutableStateOf("") }

    val product by scanViewModel._product
    val isError = scanViewModel.isErr.value
    val isLoading = scanViewModel.isLoading.value
    val error = scanViewModel.error.value

    CameraPermissionScreen {
        CameraPreview(
            onBarcodeScanned = { barcode ->
                if (barcode != lastCode) {
                    lastCode = barcode
                    Timber.i(barcode)
                    scanViewModel.getProduct(barcode)
                }
            }
        )

        when {
            isLoading -> {
                ShowLoader("Loading")
            }

            isError -> {
                ShowError((error.message ?: "Unknown error") + " error...",
                    error.toString(),
                    onClick = {
                        lastCode = ""
                        scanViewModel.hideError()
                    }
                )
            }

            product != null -> {
                ProductCard(
                    vegStaus = when (product!!.vegStatus) {
                        VegStatus.VEGAN -> "Vegan"
                        VegStatus.VEGETARIAN -> "Vegetarian"
                        VegStatus.NON_VEGETARIAN -> "Non-vegetarian"
                        else -> "Unknown"
                    },
                    name = product!!.name,
                    brand = product!!.brand,
                    imagePath = product!!.imageUrl,
                    dateCreated = Date().toString(),
                    onClickDelete = {},
                    onClickDetails = {}
                )
            }
        }

    }
}
