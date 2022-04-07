package com.example.bestapplication.repository

import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview

interface MovieRepository {

    suspend fun loadMovies(): List<MoviePreview>

    suspend fun loadGenres(): List<Genre>

    suspend fun loadActors(movieId: Int): List<Actor>

    suspend fun loadFullMovie(movieId: Int): MovieFull
}