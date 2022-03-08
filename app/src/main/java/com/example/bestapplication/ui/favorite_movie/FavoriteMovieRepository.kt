package com.example.bestapplication.ui.favorite_movie

import com.example.bestapplication.Mapper
import com.example.bestapplication.data.database.MovieDao
import com.example.bestapplication.data.database.MovieEntity
import com.example.bestapplication.data.model.MovieFull
import com.example.bestapplication.data.network.MoviesService
import com.example.bestapplication.utilites.Keys.API_KEY
import com.example.bestapplication.utilites.Keys.LANG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val api: MoviesService
) {
    var allMovies: Flow<List<MovieEntity>> = movieDao.getAllMovies()

    @ExperimentalSerializationApi
    suspend fun insertToDatabase(movieId: Int) {
        var movieFull: MovieFull? = null
        var movieDetailsEntity: MovieEntity? = null
        try {
            movieFull = withContext(Dispatchers.IO) {
                api.getMovie(movieId, API_KEY, LANG)
            }
            movieDetailsEntity = Mapper.mapMoviesListToDb(movieFull)
            movieDao.insert(movieDetailsEntity)
        } catch (e: Exception) {
            throw e
        }
    }
}