package ie.setu.vegi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.vegi.ui.screens.add.AddScreen
import ie.setu.vegi.ui.screens.details.DetailsScreen
import ie.setu.vegi.ui.screens.history.HistoryScreen
import ie.setu.vegi.ui.screens.scan.ScanScreen

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = History.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Add.route) {
            //call our 'Add' Screen Here
            AddScreen(modifier = modifier)
        }
        composable(route = History.route) {
            //call our 'History' Screen Here
            HistoryScreen(modifier = modifier,
                onClickDetails = {
                    barcode : String ->
                    navController.navigateToDonationDetails((barcode))
                })
        }
        composable(route = Scan.route) {
            //call our 'Scan' Screen Here
            ScanScreen()
        }
        composable(
            route = Details.route,
            arguments = Details.arguments
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }

    }
}

private fun NavHostController.navigateToDonationDetails(barcode: String) {
    this.navigate("details/$barcode")
}

