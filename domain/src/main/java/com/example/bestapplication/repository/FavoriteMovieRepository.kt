package com.example.bestapplication.repository


import com.example.bestapplication.favorite_movie.entity.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {

    suspend fun insertToDataBase(movieId: Int)

    suspend fun deleteFromDataBase(movie: FavoriteMovie)

    suspend fun checkInDataBase(movieId: Int): Boolean

    suspend fun getFavoriteMovie(): Flow<List<FavoriteMovie>>

    suspend fun getFindMovie(name: String): Flow<List<FavoriteMovie>>
}