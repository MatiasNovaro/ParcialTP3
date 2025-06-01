package ar.edu.ort.parcial_tp3.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ar.edu.ort.parcial_tp3.data.local.entities.PaymentCard
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentCardDao {
    @Upsert  // Esto insertará si no existe o actualizará si existe
    suspend fun upsertCard(paymentCard: PaymentCard)

    @Query("SELECT * FROM payment_cards LIMIT 1")
    fun getCard(): Flow<PaymentCard?>

}