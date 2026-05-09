package ie.setu.vegi.ui.components.history

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FilterVintage
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ie.setu.vegi.R
import ie.setu.vegi.ui.theme.VegiTheme
import java.text.DateFormat
import java.util.Date

@Composable
fun ProductCard(
    vegStaus: String?,
    name: String?,
    brand: String?,
    imagePath: String?,
    dateCreated: String,
    expandable: Boolean,
    onClickDelete: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(20.dp),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp)
    ) {
        ProductCardContent(
            vegStaus,
            name,
            brand,
            imagePath,
            dateCreated,
            expandable,
            onClickDelete,
            )
    }
}

@Composable
private fun ProductCardContent(
    vegStatus: String?,
    name: String?,
    brands: String?,
    imagePath: String?,
    dateCreated: String,
    expandable: Boolean,
    onClickDelete: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(4.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                val painter =
                if (imagePath!!.isNotEmpty()) {
                    rememberAsyncImagePainter(imagePath)
                } else {
                    painterResource(R.drawable.placeholder_image)
                }
                Image(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.productImage),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    when (vegStatus) {
                        "Vegan" -> {
                            Icon(
                                imageVector = Icons.Filled.Eco,
                                contentDescription = "Vegan Icon",
                                tint = colorResource(R.color.veganColor),
                                modifier = Modifier.padding(end = 4.dp)
                            )
                        }

                        "Vegetarian" -> {
                            Icon(
                                imageVector = Icons.Filled.FilterVintage,
                                contentDescription = "Vegetarian Icon",
                                tint = colorResource(R.color.vegetarianColor),
                                modifier = Modifier.padding(end = 4.dp)
                            )
                        }

                        else -> {}
                    }
                    Text(
                        text = vegStatus!!,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = when (vegStatus) {
                                "Vegan" -> colorResource(R.color.veganColor)
                                "Vegetarian" -> colorResource(R.color.vegetarianColor)
                                else -> colorResource(R.color.otherVegColor)
                            },
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
                Text(
                    text = name!!,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = brands!!,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            if (expandable) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) {
                            stringResource(R.string.showLess)
                        } else {
                            stringResource(R.string.showMore)
                        }
                    )
                }
            }
        }
        if (expanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(modifier = Modifier.padding(vertical = 16.dp), text = "Added $dateCreated")
                    Row() {
                        FilledTonalIconButton(onClick = {showDeleteConfirmDialog = true}) {
                            Icon(Icons.Filled.Delete, "Delete Product")
                        }
                        if (showDeleteConfirmDialog) {
                            showDeleteAlert(
                                onDismiss = { showDeleteConfirmDialog = false },
                                onDelete = onClickDelete
                            )
                        }
                    }
                }
        }
    }
}

@Composable
fun showDeleteAlert(
    onDismiss: () -> Unit,
    onDelete: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = { Text(stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_message)) },
        confirmButton = {
            Button(
                onClick = { onDelete() }
            ) { Text("Delete") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        }
    )
}


@Preview
@Composable
fun ProductCardPreview() {
    VegiTheme {
        ProductCard(
            vegStaus = "Vegetarian",
            name = "Banana",
            brand = "Bananananana",
            imagePath = "",
            dateCreated = DateFormat.getDateTimeInstance().format(Date()),
            expandable = true,
            onClickDelete = { },
        )
    }
}
