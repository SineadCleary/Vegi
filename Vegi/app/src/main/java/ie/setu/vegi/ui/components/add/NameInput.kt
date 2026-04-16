package ie.setu.vegi.ui.components.add

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.vegi.R
import ie.setu.vegi.ui.theme.VegiTheme

@Composable
fun NameInput(
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit
) {

    var name by remember { mutableStateOf("") }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        maxLines = 2,
        value = name,
        onValueChange = {
            name = it
            onNameChange(name)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.enterName)) }
    )
}

@Preview(showBackground = true)
@Composable
fun NamePreview() {
    VegiTheme {
        NameInput(
            Modifier,
            onNameChange = {})
    }
}
