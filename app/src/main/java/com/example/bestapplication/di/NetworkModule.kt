package com.example.bestapplication.di

import com.example.bestapplication.data.network.MoviesService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun baseUrl(): String = "https://api.themoviedb.org/3/"
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    private val contentType = "application/json; charset=utf-8".toMediaType()

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideApi(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)
}