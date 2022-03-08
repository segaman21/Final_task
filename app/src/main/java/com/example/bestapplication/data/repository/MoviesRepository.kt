package com.example.bestapplication.data.repository

import com.example.bestapplication.data.model.*
import com.example.bestapplication.data.network.MoviesService
import com.example.bestapplication.utilites.Keys.API_KEY
import com.example.bestapplication.utilites.Keys.LANG
import com.example.bestapplication.utilites.Keys.PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepository @Inject constructor(private val api: MoviesService) {

    suspend fun loadMovies(): List<MoviePreview> {
        val moviePreviewList: List<MoviePreview>?
        moviePreviewList = withContext(Dispatchers.IO) {
            api.loadMovies(API_KEY, LANG, PAGE).results
        }
        return moviePreviewList
    }

    suspend fun getGenres(): List<Genre> {
        val genres: List<Genre>?
        genres = withContext(Dispatchers.IO) {
            api.getGenres(API_KEY, LANG).genres
        }
        return genres
    }

    suspend fun getMovie(movieId: Int): MovieFull {
        val movieFull: MovieFull?
        movieFull = withContext(Dispatchers.IO) {
            api.getMovie(movieId, API_KEY, LANG)
        }
        return movieFull
    }

    suspend fun getActors(movieId: Int): List<Actor> {
        val actors: List<Actor>?
        actors = withContext(Dispatchers.IO) {
            api.getActors(movieId, API_KEY, LANG).actors
        }
        return actors
    }
}