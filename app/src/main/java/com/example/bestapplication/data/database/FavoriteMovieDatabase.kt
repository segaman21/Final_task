package com.example.bestapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 3)
abstract class FavoriteMovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}