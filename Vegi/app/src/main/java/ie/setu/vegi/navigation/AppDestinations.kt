package ie.setu.vegi.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ManageSearch
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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

object Home : AppDestination {
    override val icon = Icons.Filled.Home
    override val label = "Home"
    override val route = "Home"
}

object Profile : AppDestination {
    override val icon = Icons.Default.Person
    override val label = "Profile"
    override val route = "Profile"
}

object Search : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.ManageSearch
    override val label = "Search"
    override val route = "Search"
}

object Login : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.Login
    override val label = "Login"
    override val route = "Login"
}

object Register : AppDestination {
    override val icon = Icons.Default.AccountCircle
    override val label = "Register"
    override val route = "Register"
}


val bottomAppBarDestinations = listOf(History, Search, Profile)
val userSignedOutDestinations = listOf(Login, Register)
val allDestinations = listOf(History, Scan, Home, Profile, Login, Register, Search)
