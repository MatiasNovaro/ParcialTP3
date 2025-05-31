package ar.edu.ort.parcial_tp3.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import ar.edu.ort.parcial_tp3.navigation.Screens
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import ar.edu.ort.parcial_tp3.ui.screens.homepage.HomeScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentChooseScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentMethodScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentSuccessScreen

@Composable
fun Navigation(navController: NavHostController, onDestinationChanged: (String) -> Unit){
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            when (backStackEntry.destination.route) {
                Screens.Home.screen -> onDestinationChanged("Home")
            }
        }
    }
    NavHost(navController = navController, startDestination = Screens.Home.screen){
        composable(route=Screens.Home.screen){
            HomeScreen(navController= navController )
        }
        composable(route=Screens.PaymentMethodScreen.screen){
            PaymentMethodScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToChoose = { navController.navigate(Screens.PaymentChooseScreen.screen) }
            )
        }
        composable(route=Screens.PaymentMethodScreen.screen){
            PaymentChooseScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateSuccess = { navController.navigate(Screens.PaymentChooseScreen.screen)}
            )
        }
        composable(route=Screens.PaymentSuccessScreen.screen){
            PaymentSuccessScreen(
                onNavigateHome = { navController.navigate(Screens.Home.screen)}
            )
        }

    }
}