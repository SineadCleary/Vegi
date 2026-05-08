package ie.setu.vegi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.navigation.BottomAppBarProvider
import ie.setu.vegi.navigation.History
import ie.setu.vegi.navigation.NavHostProvider
import ie.setu.vegi.navigation.allDestinations
import ie.setu.vegi.ui.components.general.MenuItem
import ie.setu.vegi.ui.components.general.TopAppBarProvider
import ie.setu.vegi.ui.screens.SplashViewModel
import ie.setu.vegi.ui.screens.home.HomeScreen
import ie.setu.vegi.ui.theme.VegiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition{viewModel.isLoading.value}

//        setContent {
//            VegiTheme() {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    VegiApp(modifier = Modifier)
//                }
//            }
//        }
        setContent {
            VegiTheme() { HomeScreen() }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun VegiApp(modifier: Modifier = Modifier,
//            navController: NavHostController = rememberNavController()) {
//    val products = remember { mutableStateListOf<ProductModel>() }
//    var selectedMenuItem by remember { mutableStateOf<MenuItem?>(MenuItem.Add) }
//    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = currentNavBackStackEntry?.destination
//    val currentBottomScreen =
//        allDestinations.find { it.route == currentDestination?.route } ?: History
//
//    Scaffold(
//        modifier = modifier,
//        topBar = {
//            TopAppBarProvider(
//
//                currentScreen = currentBottomScreen,
//                canNavigateBack = navController.previousBackStackEntry != null
//            ) { navController.navigateUp() }
//         },
//        content = { paddingValues ->
//            NavHostProvider(
//                modifier = modifier,
//                navController = navController,
//                paddingValues = paddingValues)
//        },
//        bottomBar = {
//            BottomAppBarProvider(navController,
//                currentScreen = currentBottomScreen,)
//        }
//    )
//
//}

//@Preview(showBackground = true)
//@Composable
//fun MyAppPreview() {
//    VegiTheme {
//        VegiApp(modifier = Modifier)
//    }
//}
