package ar.edu.ort.parcial_tp3.navigation


sealed class Screens(val screen: String) {
    data object Home: Screens("Home")
    data object PaymentMethod: Screens("PaymentMethod")
    data object PaymentChoose: Screens("PaymentChoose")
    data object PaymentSuccess: Screens("PaymentSuccess")

}