package com.example.bestapplication.repositories

import com.example.bestapplication.models.MoviePreview


interface MovieRepository {

    suspend fun loadMovies(): List<MoviePreview>

}