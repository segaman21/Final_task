package com.example.bestapplication.movie_list.entity

data class MoviePreview(
    val id: Int,
    val title: String,
    val poster: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val genres: List<Int>,
    val minimumAge: Boolean,
)