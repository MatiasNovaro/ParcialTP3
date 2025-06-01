package ar.edu.ort.parcial_tp3.domain.model

data class PaymentUiState(
    val cardNumber: String = "",
    val cardName: String = "",
    val expirationDate: String = "",
    val cvv: String = "",
)