package ie.setu.vegi.ui.components.general

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ie.setu.vegi.R
import ie.setu.vegi.ui.screens.scan.ScanScreen

@Composable
fun ScanFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    ExtendedFloatingActionButton(
        text = { Text("Scan") },
        icon = {
            Icon(
                painter = painterResource(R.drawable.barcode_scanner_24px),
                contentDescription = "Scan barcode"
        )},
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        modifier = modifier,
    )
}