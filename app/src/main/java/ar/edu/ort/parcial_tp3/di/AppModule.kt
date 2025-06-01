package ar.edu.ort.parcial_tp3.di

import android.content.Context
import androidx.room.Room
import ar.edu.ort.parcial_tp3.data.local.network.AppParcialDB
import ar.edu.ort.parcial_tp3.data.local.session.UserSession
import ar.edu.ort.parcial_tp3.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import javax.inject.Singleton
import ar.edu.ort.parcial_tp3.data.remote.core.Config
import ar.edu.ort.parcial_tp3.data.repository.UserRepositoryImpl
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
        .baseUrl(Config.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }

    // AppModule.kt
    @Provides
    @Singleton
    fun provideUserSession(@ApplicationContext context: Context): UserSession {
        return UserSession(context)
    }

    // Room database provider
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context) =
//        Room.databaseBuilder(
//            context,
//            AppParcialDB::class.java,
//            "app_parcial_db")
//            .fallbackToDestructiveMigration(true) // opcional
//            .build()
}
