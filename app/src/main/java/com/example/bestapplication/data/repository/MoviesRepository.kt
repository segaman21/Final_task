package com.example.bestapplication.data.repository

import com.example.bestapplication.data.model.*
import com.example.bestapplication.data.network.MoviesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepository @Inject constructor(private val api: MoviesService) {
    private val lang = "en-US"
    private val key = "641666dd6d279ab35afbe0cdfe384006"
    private val page = 1

    suspend fun loadMovies(): List<MoviePreview> {
        val moviePreviewList: List<MoviePreview>?
        moviePreviewList = withContext(Dispatchers.IO) {
            api.loadMovies(key, lang, page).results
        }
        return moviePreviewList
    }

    suspend fun getGenres(): List<Genre> {
        val genres: List<Genre>?
        genres = withContext(Dispatchers.IO) {
            api.getGenres(key, lang).genres
        }
        return genres
    }

    suspend fun getMovie(movieId: Int): MovieFull {
        val movieFull: MovieFull?
        movieFull = withContext(Dispatchers.IO) {
            api.getMovie(movieId, key, lang)
        }
        return movieFull

    }

    suspend fun getActors(movieId: Int): List<Actor> {
        val actors: List<Actor>?
        actors = withContext(Dispatchers.IO) {
            api.getActors(movieId, key, lang).actors
        }
        return actors
    }
}