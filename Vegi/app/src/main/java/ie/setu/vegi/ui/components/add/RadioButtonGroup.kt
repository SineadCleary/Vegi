package ie.setu.vegi.ui.components.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.vegi.R
import ie.setu.vegi.ui.theme.VegiTheme

@Composable
fun RadioButtonGroup(modifier: Modifier = Modifier,
                     selectedVegStatus: String,
                     onVegStatusChange: (String) -> Unit) {

    val radioOptions = listOf(
        stringResource(R.string.vegan),
        stringResource(R.string.vegetarian),
        stringResource(R.string.nonVeg),
        stringResource(R.string.unknown),
    )
//    var vegStatus by remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        radioOptions.forEach { vegStatusText ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (vegStatusText == selectedVegStatus),
                    onClick = {
//                        vegStatus = vegStatusText
                        onVegStatusChange(vegStatusText)
                    }
                )
                Text(
                    text = vegStatusText,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RadioButtonPreview() {
    VegiTheme() {
        RadioButtonGroup(
            Modifier,
            selectedVegStatus = "Vegan",
            onVegStatusChange = {}
        )
    }
}
