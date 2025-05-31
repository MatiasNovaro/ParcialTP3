package ar.edu.ort.parcial_tp3.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.ort.parcial_tp3.domain.model.PaymentUiState

@Entity(tableName = "payment_cards")
data class PaymentCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cardNumber: String,
    val cardName: String,
    val expirationDate: String,
    val cvv: String
)

fun PaymentCardtoPaymentUiState(paymentCard: PaymentCard): PaymentUiState {
    return PaymentUiState(
        cardNumber = paymentCard.cardNumber,
        cardName = paymentCard.cardName,
        expirationDate = paymentCard.expirationDate,
        cvv = paymentCard.cvv
    )
}