package ar.edu.ort.parcial_tp3.navigation

import ProfileScreen
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.navigation.Screens.ChangeEmailScreen
import ar.edu.ort.parcial_tp3.navigation.Screens.ChangePasswordScreen
import ar.edu.ort.parcial_tp3.navigation.Screens.SecurityScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.BestSellerScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.CartScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.HomeScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.NotificationScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.ProductDetailScreen
import ar.edu.ort.parcial_tp3.ui.screens.homepage.SearchScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.ForgotPasswordResetScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.ForgotPasswordScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.LoginScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.RegisterScreen
import ar.edu.ort.parcial_tp3.ui.screens.onboarding.SplashScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentChooseScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentMethodScreen
import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentSuccessScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.AccountScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.ChangeEmailScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.ChangePasswordScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.FaqScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.PrivacyScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.SecurityScreen
import ar.edu.ort.parcial_tp3.ui.screens.settings.SettingNotificationScreen
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
                navController=navController
            )
        }
        composable(route=Screens.PaymentChooseScreen.screen){
            PaymentChooseScreen(
                navController=navController
            )
        }
        composable(route=Screens.PaymentSuccessScreen.screen){
            PaymentSuccessScreen(
                navController=navController
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

        composable(
            route = Screens.BestSellerScreen.screen,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            BestSellerScreen(
                navController = navController,
                onAddToCart = {},
                category = category
            )
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
        composable(route=Screens.NotificationScreen.screen) {
            NotificationScreen(
                navController = navController,
            )
        }
        composable(route=Screens.SearchScreen.screen) {
            SearchScreen(
                navController = navController,
            )
        }
        composable(route= Screens.AccountScreen.screen) {
            AccountScreen(
                navController = navController
            )
        }
        composable(route= Screens.PrivacyScreen.screen) {
            PrivacyScreen(
                navController = navController
            )
        }
        composable(route= Screens.SecurityScreen.screen) {
            SecurityScreen(
                navController = navController
            )
        }
        composable(route= Screens.ChangePasswordScreen.screen) {
            ChangePasswordScreen(
                navController = navController
            )
        }
        composable(route= Screens.ChangeEmailScreen.screen) {
            ChangeEmailScreen(
                navController = navController
            )
        }
        composable(route= Screens.FaqScreen.screen) {
            FaqScreen(
                navController = navController
            )
        }
        composable(route= Screens.SettingsNotificationScreen.screen) {
            SettingNotificationScreen(
                navController = navController
            )
        }
    }
}