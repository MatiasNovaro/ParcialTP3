package ar.edu.ort.parcial_tp3.navigation


sealed class Screens(val screen: String) {
    data object Home: Screens("Home")
    data object SplashScreen: Screens("SplashScreen")
    data object PaymentMethodScreen: Screens("PaymentMethodScreen")
    data object PaymentChooseScreen: Screens("PaymentChooseScreen")
    data object PaymentSuccessScreen: Screens("PaymentSuccessScreen")
}