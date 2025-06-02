package ar.edu.ort.parcial_tp3.navigation

import ProfileScreen
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.ui.screens.homepage.BestSellerScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.CartScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.HomeScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.ProductDetailScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.ForgotPasswordResetScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.ForgotPasswordScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.LoginScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.RegisterScreen
import ar.edu.ort.parcial_tp3.ui.screens.onboarding.SplashScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentChooseScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentMethodScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentSuccessScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.SettingsScreen

@Composable
fun Navigation(
    navController: NavHostController,
    onDestinationChanged: (String) -> Unit,
    modifier: Modifier
){
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            when (backStackEntry.destination.route) {
                Screens.Home.screen -> onDestinationChanged("Home")
            }
        }
    }
    NavHost(navController = navController, startDestination = Screens.SplashScreen.screen, modifier = modifier){
        composable(route=Screens.Home.screen){
            HomeScreen(navController= navController )
        }
        composable(route=Screens.PaymentMethodScreen.screen){
            PaymentMethodScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToChoose = { navController.navigate(Screens.PaymentChooseScreen.screen) }
            )
        }
        composable(route=Screens.PaymentChooseScreen.screen){
            PaymentChooseScreen(
                onBackClick = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(route=Screens.PaymentSuccessScreen.screen){
            PaymentSuccessScreen(
                onNavigateHome = { navController.navigate(Screens.Home.screen)}
            )
        }
        composable(route=Screens.SplashScreen.screen){
            SplashScreen(
                onGetStartedClick = {navController.navigate("LoginScreen")},navController = navController)
        }
        composable(route=Screens.LoginScreen.screen){
            LoginScreen(
                onGetStartedClick = {},navController = navController)
        }
        composable(route=Screens.RegisterScreen.screen)
        { RegisterScreen(
            onRegisterClick = {},navController = navController)
        }
        composable(route=Screens.ForgotPasswordScreen.screen)
        { ForgotPasswordScreen(
            onNextClick = {navController.navigate("ForgotPasswordResetScreen")},navController = navController)
        }
        composable(route=Screens.BestSellerScreen.screen) {
            BestSellerScreen(
                navController = navController,
                onAddToCart ={},
            )
        }
        composable(route=Screens.CartScreen.screen) {
            CartScreen(
                onBackClick = { },
                navController = navController,
            )
        }
        composable(route=Screens.ProductDetailScreen.screen) {
            val product = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Product>("product")
            if (product != null) {
                ProductDetailScreen(
                    product = product,
                    navController = navController
                )
            }
        }
        composable(route=Screens.ProfileScreen.screen) {
            ProfileScreen(
                navController = navController
            )
        }
        composable(route=Screens.SettingsScreen.screen) {
            SettingsScreen(
                navController = navController
            )
        }
        composable(route=Screens.ForgotPasswordResetScreen.screen) {
            ForgotPasswordResetScreen(
                navController = navController,
            )
        }
    }
}