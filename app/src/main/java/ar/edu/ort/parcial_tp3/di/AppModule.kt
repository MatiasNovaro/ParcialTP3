package ar.edu.ort.parcial_tp3.di

import android.content.Context
import android.media.audiofx.DynamicsProcessing.Config
import androidx.room.Room
import ar.edu.ort.parcial_tp3.data.local.dao.PaymentCardDao
import ar.edu.ort.parcial_tp3.data.local.network.AppParcialDB
import ar.edu.ort.parcial_tp3.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import javax.inject.Singleton
import ar.edu.ort.parcial_tp3.data.repository.UserRepositoryImpl
import ar.edu.ort.parcial_tp3.data.repository.PaymentCardRepositoryImpl
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.domain.repository.PaymentCardRepository
import ar.edu.ort.parcial_tp3.domain.repository.UserRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit provider
    @Provides
    @Singleton
    fun provideRetrofit(): ApiService = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    fun providePaymentCardRepository(
        paymentCardDao: PaymentCardDao
    ): PaymentCardRepository {
        return PaymentCardRepositoryImpl(paymentCardDao)
    }

    @Provides
    fun providePaymentCardDao(
        appParcialDB: AppParcialDB
    ): PaymentCardDao {
        return appParcialDB.paymentCardDao
    }

    // Room database provider
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppParcialDB::class.java,
            "app_parcial_db")
            .fallbackToDestructiveMigration(true) // opcional
           .build()
}
