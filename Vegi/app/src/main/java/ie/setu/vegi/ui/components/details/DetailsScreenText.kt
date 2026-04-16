package ie.setu.vegi.ui.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.vegi.ui.theme.VegiTheme

@Composable
fun DetailsScreenText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            top = 24.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Text(
            text = "Update product details",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    VegiTheme {
        DetailsScreenText(modifier = Modifier)
    }
}
