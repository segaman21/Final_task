package com.example.bestapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDatabaseEntity::class], version = 1)
abstract class FavoriteMovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}