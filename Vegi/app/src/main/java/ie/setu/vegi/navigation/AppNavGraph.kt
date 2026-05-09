package ie.setu.vegi.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.vegi.ui.screens.history.HistoryScreen
import ie.setu.vegi.ui.screens.home.HomeScreen
import ie.setu.vegi.ui.screens.login.LoginScreen
import ie.setu.vegi.ui.screens.profile.ProfileScreen
import ie.setu.vegi.ui.screens.register.RegisterScreen
import ie.setu.vegi.ui.screens.scan.ScanScreen

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

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
        composable(route = Home.route) {
            //call our 'Home' Screen Here
            HomeScreen(modifier = modifier)
        }
        composable(route = Login.route) {
            //call our 'Login' Screen Here
            LoginScreen(
                navController = navController,
                onLogin = { navController.popBackStack() }
            )
        }

        composable(route = Register.route) {
            //call our 'Register' Screen Here
            RegisterScreen(
                navController = navController,
                onRegister = { navController.popBackStack() }
            )
        }
        composable(route = Profile.route) {
            ProfileScreen(
                onSignOut = {
                    navController.popBackStack()
                    navController.navigate(Login.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                },
            )
        }

    }
}

private fun NavHostController.navigateToDonationDetails(barcode: String) {
    this.navigate("details/$barcode")
}

