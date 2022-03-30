package com.example.bestapplication.repository

import com.example.bestapplication.Mapper
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
        val moviePreviewList: List<MoviePreview>?
        moviePreviewList = withContext(Dispatchers.IO) {
            Mapper.mapMoviePreview(api.getMovieList(apiKey, lang, page).results)
        }
        return moviePreviewList
    }

    override suspend fun loadGenres(): List<Genre> {
        val genres: List<Genre>?
        genres = withContext(Dispatchers.IO) {
            Mapper.mapGenre(api.getGenres(apiKey, lang).genres)
        }
        return genres
    }

    override suspend fun loadActors(movieId: Int): List<Actor> {
        val actors: List<Actor>?
        actors = withContext(Dispatchers.IO) {
            Mapper.mapActors(api.getActors(movieId, apiKey, lang).actors)
        }
        return actors
    }

    override suspend fun loadFullMovie(movieId: Int): MovieFull {
        val movieFull: MovieFull?
        movieFull = withContext(Dispatchers.IO) {
            Mapper.mapFullMovie(api.getFullMovie(movieId, apiKey, lang))
        }
        return movieFull
    }
}
