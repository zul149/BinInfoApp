package com.example.binlist.bininfo.di

import android.content.Context
import androidx.room.Room
import com.example.binlist.bininfo.data.BinApi
import com.example.binlist.bininfo.data.BinDao
import com.example.binlist.bininfo.data.BinDatabase
import com.example.binlist.bininfo.data.BinRepository
import com.example.binlist.bininfo.data.BinRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Модуль Hilt для предоставления зависимостей
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Метод для предоставления Retrofit с базовым URL
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Логгер для Retrofit
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // Создаем OkHttpClient с логгером
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            }
            .build()

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .client(client) // Подключаем OkHttpClient
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    // Метод для предоставления экземпляра BinApi
    @Provides
    @Singleton
    fun provideBinApi(retrofit: Retrofit): BinApi {
        return retrofit.create(BinApi::class.java)
    }
    // Метод для предоставления репозитория
    @Provides
    @Singleton
    fun provideBinRepository(binApi: BinApi, binDao: BinDao): BinRepository {
        return BinRepositoryImpl(binApi, binDao)
    }
    // Метод для предоставления DAO Room
    @Provides
    @Singleton
    fun provideBinDao(@ApplicationContext context: Context): BinDao {
        return Room.databaseBuilder(context, BinDatabase::class.java, "bin_database")
            .build()
            .binDao()
    }
}