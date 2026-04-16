package ie.setu.vegi.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Eco
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val icon: ImageVector
    val label: String
    val route: String
}

object Add : AppDestination {
    override val icon = Icons.Filled.AddCircle
    override val label = "Add"
    override val route = "add"
}

object History : AppDestination {
    override val icon = Icons.Filled.Eco
    override val label = "History"
    override val route = "history"
}

object Details : AppDestination {
    override val icon = Icons.Filled.Details
    override val label = "Details"
    const val idArg = "id"
    override val route = "details/{$idArg}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.IntType }
    )
}

object Scan : AppDestination {
    override val icon = Icons.Filled.CameraAlt
    override val label = "Scan"
    override val route = "scan"
}


val bottomAppBarDestinations = listOf(History, Add, Scan)
val allDestinations = listOf(History, Add, Details, Scan)
