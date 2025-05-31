package ar.edu.ort.parcial_tp3.data.local.network

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.ort.parcial_tp3.data.local.dao.PaymentCardDao
import ar.edu.ort.parcial_tp3.data.local.entities.PaymentCard

@Database(
    entities=[PaymentCard::class],
    version=1,
    exportSchema = false
)
abstract class AppParcialDB: RoomDatabase() {
    abstract val paymentCardDao: PaymentCardDao
}