package ar.edu.ort.parcial_tp3.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import ar.edu.ort.parcial_tp3.ui.screens.Home
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentMethodScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentChooseScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentSuccessScreen


@Composable
fun Navigation(navController: NavHostController, onDestinationChanged: (String) -> Unit){
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            when (backStackEntry.destination.route) {
                Screens.Home.screen -> onDestinationChanged("Home")
                Screens.PaymentMethod.screen -> onDestinationChanged("PaymentMethod")
                Screens.PaymentChoose.screen -> onDestinationChanged("PaymentChoose")
                Screens.PaymentSuccess.screen -> onDestinationChanged("PaymentSuccess")
            }
        }
    }
    NavHost(navController = navController, startDestination = Screens.Home.screen){
        composable(route=Screens.Home.screen){
            Home(navController= navController )
        }
        composable(route=Screens.PaymentMethod.screen){
            PaymentMethodScreen(onBackClick = { navController.popBackStack() },
                onNavigateToChoose = { navController.navigate(Screens.PaymentChoose.screen) })
        }
        composable(route=Screens.PaymentChoose.screen){
            PaymentChooseScreen(onBackClick = { navController.popBackStack() }) {
                navController.navigate(
                    Screens.PaymentSuccess.screen
                )
            }
        }
        composable(route=Screens.PaymentSuccess.screen){
            PaymentSuccessScreen(onNavigateHome = { navController.navigate(Screens.Home.screen) })
        }
    }

}