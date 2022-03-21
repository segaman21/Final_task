package com.example.bestapplication.models

data class MoviePreview(
    val id: Int,
    val title: String,
    val poster: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val genres: List<Int>,
    val minimumAge: Boolean,
)

data class Genre(
    val id: Int,
    val name: String
)
