package com.example.bestapplication.favorite_movie.entity

data class MovieDatabaseEntity(
    val id: Int,
    val poster: String,
    val title: String,
    val ratings: Float,
    val genres: String,
    val runtime: Int
)