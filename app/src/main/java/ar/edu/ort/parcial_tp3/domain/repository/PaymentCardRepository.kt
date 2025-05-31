package ar.edu.ort.parcial_tp3.domain.repository


import ar.edu.ort.parcial_tp3.data.local.entities.PaymentCard
import kotlinx.coroutines.flow.Flow

interface PaymentCardRepository {
    suspend fun upsertCard(paymentCard: PaymentCard)

    suspend fun getCard(): Flow<PaymentCard?>

}