package com.example.bestapplication.di.modules

import com.example.bestapplication.network.NetworkApi
import com.example.bestapplication.utilites.Keys.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): NetworkApi =
        retrofit.create(NetworkApi::class.java)

}