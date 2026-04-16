package ie.setu.vegi.ui.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.vegi.R
import ie.setu.vegi.data.models.VegStatus
import ie.setu.vegi.ui.theme.VegiTheme

@Composable
fun FilterChipRow(
    selectedFilters: Set<VegStatus>,
    onFilterChanged: (VegStatus, Boolean) -> Unit
) {
//    val vegan = stringResource(R.string.vegan)
//    val vegetarian = stringResource(R.string.vegetarian)
//    val unknown = stringResource(R.string.unknown)
//    val nonVeg = stringResource(R.string.nonVeg)

    val filters = listOf(
        VegStatus.VEGAN,
        VegStatus.VEGETARIAN,
        VegStatus.UNKNOWN,
        VegStatus.NON_VEGETARIAN
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(filters) { filter ->
            val label = when (filter) {
                VegStatus.VEGAN -> stringResource(R.string.vegan)
                VegStatus.VEGETARIAN -> stringResource(R.string.vegetarian)
                VegStatus.UNKNOWN -> stringResource(R.string.unknown)
                VegStatus.NON_VEGETARIAN -> stringResource(R.string.nonVeg)
            }
            FilterChip(
                title = label,
                selected = selectedFilters.contains(filter)
            ) { selected ->
                onFilterChanged(filter, selected)
            }
        }
    }
}

@Composable
fun FilterChip(
    title: String,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit
) {
    FilterChip(
        onClick = { onSelectedChange(!selected) },
        label = {
            Text(title)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

@Preview(showBackground = true)
@Composable
fun FilterChipRowPreview() {
    VegiTheme {
        FilterChipRow(
            selectedFilters = setOf(VegStatus.VEGETARIAN, VegStatus.VEGAN),
            onFilterChanged = { _, _  -> }
        )
    }
}