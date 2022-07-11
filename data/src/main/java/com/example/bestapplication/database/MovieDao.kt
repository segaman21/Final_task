package com.example.bestapplication.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movie")
    fun getAllMovies(): Flow<List<MovieDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDatabaseEntity)

    @Delete
    suspend fun delete(movie: MovieDatabaseEntity)

    @Query("SELECT * FROM favorite_movie WHERE id=:movieId")
    fun check(movieId: Int): MovieDatabaseEntity?

    @Query("SELECT * FROM favorite_movie WHERE original_title LIKE '%' || :name || '%'")
    fun getFindMovies(name: String): List<MovieDatabaseEntity>
}