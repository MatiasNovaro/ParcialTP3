package ar.edu.ort.parcial_tp3.navigation


sealed class Screens(val screen: String) {
    data object Home: Screens("Home")
    data object SplashScreen: Screens("SplashScreen")
    data object LoginScreen: Screens("LoginScreen")
    data object RegisterScreen: Screens("RegisterScreen")
    data object ForgotPasswordScreen: Screens("ForgotPasswordScreen")
    data object ForgotPasswordResetScreen: Screens("ForgotPasswordResetScreen")
    data object PaymentMethodScreen: Screens("PaymentMethodScreen")
    data object PaymentChooseScreen: Screens("PaymentChooseScreen")
    data object PaymentSuccessScreen: Screens("PaymentSuccessScreen")
    data object BestSellerScreen: Screens("BestSellerScreen")
    data object CartScreen: Screens("CartScreen")
    data object ProductDetailScreen:Screens("ProductDetailScreen")
    data object ProfileScreen: Screens("ProfileScreen")
}

sealed class HomeBottomBarScreens(val screen: String) {
    data object Home : HomeBottomBarScreens("Home")
    data object SplashScreen : HomeBottomBarScreens("SplashScreen")
    data object LoginScreen : HomeBottomBarScreens("LoginScreen")
    data object RegisterScreen : HomeBottomBarScreens("RegisterScreen")
    data object BestSellerScreen: HomeBottomBarScreens("BestSellerScreen")
    data object CartScreen: HomeBottomBarScreens("CartScreen")
    data object ProductDetailScreen:HomeBottomBarScreens("ProductDetailScreen")
    data object ProfileScreen: HomeBottomBarScreens("ProfileScreen")
}