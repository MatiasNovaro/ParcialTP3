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
    data object SettingsScreen: Screens("SettingsScreen")
    data object AccountScreen: Screens("AccountScreen")
    data object NotificationScreen: Screens("NotificationScreen")
    data object PrivacyScreen: Screens("PrivacyScreen")
    data object FaqScreen: Screens("FaqScreen")
    data object SecurityScreen: Screens("SecurityScreen")
    data object ChangePasswordScreen: Screens("ChangePasswordScreen")
    data object ChangeEmailScreen: Screens("ChangeEmailScreen")
    data object SearchScreen: Screens("SearchScreen")

}

sealed class HomeBottomBarScreens(val screen: String) {
    data object Home : HomeBottomBarScreens("Home")
    data object CartScreen: HomeBottomBarScreens("CartScreen")
    data object ProfileScreen: HomeBottomBarScreens("ProfileScreen")
    data object SearchScreen: Screens("SearchScreen")
}