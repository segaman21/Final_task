package com.example.bestapplication.repository


import com.example.bestapplication.favorite_movie.entity.MovieDatabaseEntity
import kotlinx.coroutines.flow.Flow


interface FavoriteMovieRepository {

    suspend fun insertToDataBase(movieId: Int)

    suspend fun deleteFromDataBase(movie: MovieDatabaseEntity)

    suspend fun checkInDataBase(movieId: Int): Boolean

    suspend fun getFavoriteMovie(): Flow<List<MovieDatabaseEntity>>
}