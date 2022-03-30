package com.example.bestapplication.repository

import com.example.bestapplication.Mapper
import com.example.bestapplication.model.MovieFullApi
import com.example.bestapplication.database.MovieDao
import com.example.bestapplication.database.MovieEntity
import com.example.bestapplication.favorite_movie.entity.MovieDatabaseEntity
import com.example.bestapplication.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val api: NetworkApi
) : FavoriteMovieRepository {

    private val lang = "en-US"
    private val apiKey = "641666dd6d279ab35afbe0cdfe384006"


    @ExperimentalSerializationApi
    override suspend fun insertToDataBase(movieId: Int) {
        val movieFull: MovieFullApi
        val movieDetailsEntity: MovieEntity
        try {
            movieFull = withContext(Dispatchers.IO) {
                api.getFullMovie(movieId, apiKey, lang)
            }
            movieDetailsEntity = Mapper.mapMoviesListToDb(movieFull)
            withContext(Dispatchers.IO) {
                movieDao.insert(movieDetailsEntity)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun deleteFromDataBase(movie: MovieDatabaseEntity) =
        withContext(Dispatchers.IO) {
            movieDao.delete(Mapper.mapMoviesInDb(movie))
        }

    override suspend fun checkInDataBase(movieId: Int): Boolean = withContext(Dispatchers.IO) {
        return@withContext movieDao.check(movieId) != null
    }


    override suspend fun getFavoriteMovie(): Flow<List<MovieDatabaseEntity>> =
        withContext(Dispatchers.IO) {
            movieDao.getAllMovies().flatMapMerge {
                flowOf(Mapper.mapMoviesFromDb(it))
            }
        }
}