package ar.edu.ort.parcial_tp3.navigation


sealed class Screens(val screen: String) {
    data object Home: Screens("Home")
    data object SplashScreen: Screens("SplashScreen")
    data object LoginScreen: Screens("LoginScreen")
    data object RegisterScreen: Screens("RegisterScreen")
    data object ForgotPasswordScreen: Screens("ForgotPasswordScreen")
    data object ForgotPasswordResetScreen: Screens("ForgotPasswordResetScreen")
}