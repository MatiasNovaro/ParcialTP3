package ar.edu.ort.parcial_tp3.di

import android.content.Context
import ar.edu.ort.parcial_tp3.data.local.network.AppParcialDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import javax.inject.Singleton
import ar.edu.ort.parcial_tp3.data.remote.core.Config
import dagger.hilt.android.qualifiers.ApplicationContext

// package ar.edu.ort.parcial_tp3.di

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    // Retrofit provider
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit = Retrofit.Builder()
//        .baseUrl(Config.baseUrl) // Asumo que lo definiste en data.remote.core.Config
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiExample =
//        retrofit.create(ApiExample::class.java)
//
//    // Room database provider
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppParcialDB =
//        Room.databaseBuilder(context, AppParcialDB::class.java, "app_parcial_db")
//            .fallbackToDestructiveMigration() // opcional
//            .build()
//
//    @Provides
//    fun provideDao(database: AppParcialDB): DaoExample =
//        database.daoExample()
//
//    // Repository provider
//    @Provides
//    fun provideRepository(
//        api: ApiExample,
//        dao: DaoExample
//    ): RepoInterface = RepositoryImpl(api, dao)
//}
