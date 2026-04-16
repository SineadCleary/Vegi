package ie.setu.vegi.ui.screens.add

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.vegi.R
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.models.fakeProducts
import ie.setu.vegi.ui.components.add.AddButton
import ie.setu.vegi.ui.components.add.AddScreenText
import ie.setu.vegi.ui.components.add.NameInput
import ie.setu.vegi.ui.components.add.RadioButtonGroup
import ie.setu.vegi.ui.screens.history.HistoryViewModel
import ie.setu.vegi.ui.theme.VegiTheme
import java.io.File

fun saveImageToInternalStorage(context: Context, uri: Uri, fileName: String): String? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.filesDir, fileName)
        inputStream?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        file.absolutePath // store this in ProductModel
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun AddScreen(modifier: Modifier = Modifier,
              historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val products = historyViewModel.uiProducts.collectAsState().value

    var vegStatus by remember { mutableStateOf("Vegan") }
    var name by remember { mutableStateOf("Unnamed item") }
    var imagePath by remember { mutableStateOf("") }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()

    ) { uri ->
        if (uri != null) {
            if (uri != null) {
                // Save image to internal storage
                val savedPath = saveImageToInternalStorage(
                    context = context,
                    uri = uri,
                    fileName = "product_${System.currentTimeMillis()}.jpg"
                )
                if (savedPath != null) {
                    imagePath = savedPath
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column {
            Column(
                modifier = modifier.padding(
                    start = 24.dp,
                    end = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(30.dp),
            ) {
                AddScreenText()
                NameInput(
                    modifier = modifier,
                    onNameChange = { name = it }
                )
                Button(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    elevation = ButtonDefaults.buttonElevation(20.dp)
                ) {
                    Icon(Icons.Filled.ImageSearch, "Pick image")
                    Spacer(modifier.width(width = 4.dp))
                    Text(
                        text = stringResource(R.string.pickImage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
                RadioButtonGroup(
                    modifier = modifier,
                    selectedVegStatus = vegStatus,
                    onVegStatusChange = { vegStatus = it }
                )
                AddButton(
                    modifier = modifier,
                    product = ProductModel(
//                        vegStatus = vegStatus,
                        name = name,
                        imageUrl = imagePath
                    ),
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPreviewScreen() {
    VegiTheme {
        PreviewAddScreen( modifier = Modifier,
            products = fakeProducts.toMutableStateList())
    }
}

@Composable
fun PreviewAddScreen(modifier: Modifier = Modifier,
                     products: SnapshotStateList<ProductModel>
) {
    var vegStatus by remember { mutableStateOf("Vegan") }
    var name by remember { mutableStateOf("Example item") }

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            AddScreenText()
            NameInput(
                modifier = modifier,
                onNameChange = { name = it }
            )
            Button(onClick = { }) {
                Icon(Icons.Filled.ImageSearch, "Pick image")
                Spacer(modifier.width(width = 4.dp))
                Text(
                    text = stringResource(R.string.pickImage),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            RadioButtonGroup(
                modifier = modifier,
                selectedVegStatus = "Vegan",
                onVegStatusChange = { vegStatus = it }
            )
//            AddButton (
//                modifier = modifier,
//                product = ProductModel(
//                    vegStatus = vegStatus,
//                    name = name,
//                )
//            )
        }
    }
}
