package com.example.bestapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bestapplication.data.model.Genre
import kotlinx.serialization.SerialName

@Entity(tableName = "favorite_movie")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "poster_path") val poster: String,
    @ColumnInfo(name = "original_title") val title: String,
    @ColumnInfo(name = "vote_average") val ratings: Float,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "runtime") val runtime: Int
)
