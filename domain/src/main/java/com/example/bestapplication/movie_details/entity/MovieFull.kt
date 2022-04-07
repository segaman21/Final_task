package com.example.bestapplication.movie_details.entity

import com.example.bestapplication.movie_list.entity.Genre


data class MovieFull(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val genres: List<Genre>,
    val minimumAge: Boolean,
    val runtime: Int,
    val poster: String
)