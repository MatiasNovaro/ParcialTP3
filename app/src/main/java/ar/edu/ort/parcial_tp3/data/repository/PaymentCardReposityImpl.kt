package ar.edu.ort.parcial_tp3.data.repository

import ar.edu.ort.parcial_tp3.data.local.dao.PaymentCardDao
import ar.edu.ort.parcial_tp3.data.local.entities.PaymentCard
import ar.edu.ort.parcial_tp3.domain.repository.PaymentCardRepository
import kotlinx.coroutines.flow.Flow

class PaymentCardRepositoryImpl(
    private val paymentCardDao: PaymentCardDao
):PaymentCardRepository {
    override suspend fun upsertCard(paymentCard: PaymentCard) {
        paymentCardDao.upsertCard(paymentCard)
    }

    override suspend fun getCard(): Flow<PaymentCard?> {
        return paymentCardDao.getCard()
    }

}