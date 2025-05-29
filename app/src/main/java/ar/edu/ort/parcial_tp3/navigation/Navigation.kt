package ar.edu.ort.parcial_tp3.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import ar.edu.ort.parcial_tp3.navigation.Screens
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import ar.edu.ort.parcial_tp3.ui.screens.Home

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
            Home(navController= navController )
        }
    }
}