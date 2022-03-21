package com.example.bestapplication.presentation.favorite_movie

import com.example.data.Mapper
import com.example.data.database.MovieDao
import com.example.data.database.MovieEntity
import com.example.data.model.MovieFullApi
import com.example.bestapplication.utilites.Keys.API_KEY
import com.example.bestapplication.utilites.Keys.LANG
import com.example.data.network.MoviesService
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
        val movieFull: MovieFullApi
        val movieDetailsEntity: MovieEntity
        try {
            movieFull = withContext(Dispatchers.IO) {
                api.getMovie(movieId, API_KEY, LANG)
            }
            movieDetailsEntity = Mapper.mapMoviesListToDb(movieFull)
            withContext(Dispatchers.IO) {
                movieDao.insert(movieDetailsEntity)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteFromDatabase(movie:MovieEntity) = withContext(Dispatchers.IO) {
        movieDao.delete(movie)
    }

    suspend fun checkMovieInDatabase(movieId: Int): Boolean = withContext(Dispatchers.IO) {
        return@withContext movieDao.check(movieId) != null
    }
}