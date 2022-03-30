package com.example.bestapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class FavoriteMovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}