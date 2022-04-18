package com.example.bestapplication.di.modules

import android.content.Context
import androidx.room.Room
import com.example.bestapplication.R
import com.example.bestapplication.database.FavoriteMovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule() {

    @Singleton
    @Provides
    fun provideYourDatabase(context: Context) = Room.databaseBuilder(
        context,
        FavoriteMovieDatabase::class.java,
        "${R.string.name_database}"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: FavoriteMovieDatabase) =
        db.movieDao()
}