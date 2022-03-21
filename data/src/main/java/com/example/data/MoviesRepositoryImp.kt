package com.example.data

import com.example.bestapplication.models.MoviePreview
import com.example.bestapplication.repositories.MovieRepository
import com.example.data.Keys.API_KEY
import com.example.data.Keys.LANG
import com.example.data.Keys.PAGE
import com.example.data.model.Actor
import com.example.data.model.Genre
import com.example.data.model.MovieFullApi
import com.example.data.network.MoviesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesRepositoryImp @Inject constructor(private val api: MoviesService) : MovieRepository {

    override suspend fun loadMovies(): List<MoviePreview> {
        val moviePreviewList: List<MoviePreview>?
        moviePreviewList = withContext(Dispatchers.IO) {

            Mapper.mapFullMovie(api.loadMovies(API_KEY, LANG, PAGE).results)
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

    suspend fun getMovie(movieId: Int): MovieFullApi {
        val movieFull: MovieFullApi?
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

object Keys {
    const val LANG = "en-US"
    const val API_KEY = "641666dd6d279ab35afbe0cdfe384006"
    const val PAGE = 1
    const val ID = "id"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_URL = "https://image.tmdb.org/t/p/original/"
}