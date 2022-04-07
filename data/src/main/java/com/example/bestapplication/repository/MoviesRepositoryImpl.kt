package com.example.bestapplication.repository

import com.example.bestapplication.Mapper
import com.example.bestapplication.model.ActorApi
import com.example.bestapplication.model.GenreApi
import com.example.bestapplication.model.MoviePreviewApi
import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview
import com.example.bestapplication.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepositoryImpl @Inject constructor(private val api: NetworkApi) : MovieRepository {

    private val lang = "en-US"
    private val apiKey = "641666dd6d279ab35afbe0cdfe384006"
    private val page = 1

    override suspend fun loadMovies(): List<MoviePreview> {
        val moviePreviewList = withContext(Dispatchers.IO) {
            api.getMovieList(apiKey, lang, page).results
        }
        return moviePreviewList.map { Mapper.mapMoviePreview(it) }
    }

    override suspend fun loadGenres(): List<Genre> {
        val genres = withContext(Dispatchers.IO) {
            api.getGenres(apiKey, lang).genres
        }
        return genres.map { Mapper.mapGenre(it) }
    }

    override suspend fun loadActors(movieId: Int): List<Actor> {
        val actors = withContext(Dispatchers.IO) {
            api.getActors(movieId, apiKey, lang).actors
        }
        return actors.map { Mapper.mapActors(it) }
    }

    override suspend fun loadFullMovie(movieId: Int): MovieFull {
        val movieFull = withContext(Dispatchers.IO) {
            Mapper.mapFullMovie(api.getFullMovie(movieId, apiKey, lang))
        }
        return movieFull
    }
}
