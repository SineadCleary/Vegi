package ie.setu.vegi.ui.screens.scan

import androidx.compose.runtime.Composable
import ie.setu.vegi.ui.components.camera.CameraPermissionScreen
import ie.setu.vegi.ui.components.camera.CameraPreview
import timber.log.Timber

@Composable
fun ScanScreen() {
    CameraPermissionScreen {
        CameraPreview(
            onBarcodeScanned = { barcode ->
                Timber.i(barcode)
            }
        )
    }
}
