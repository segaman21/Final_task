package com.example.bestapplication.di

import android.content.Context
import androidx.room.Room
import com.example.bestapplication.R
import com.example.bestapplication.data.database.FavoriteMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteMovieDatabase::class.java,
        "${R.string.name_database}"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: FavoriteMovieDatabase) = db.movieDao()
}