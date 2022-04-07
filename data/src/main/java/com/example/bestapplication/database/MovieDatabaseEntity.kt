package com.example.bestapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class MovieDatabaseEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "poster_path")
    val poster: String,

    @ColumnInfo(name = "original_title")
    val title: String,

    @ColumnInfo(name = "vote_average")
    val ratings: Float,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "runtime")
    val runtime: Int
)
