package com.example.bestapplication.favorite_movie.usecase

import com.example.bestapplication.favorite_movie.entity.MovieDatabaseEntity
import com.example.bestapplication.repository.FavoriteMovieRepository
import javax.inject.Inject

class DeleteFromDataBase @Inject constructor(private val movieRepository: FavoriteMovieRepository) {

    suspend fun invoke(movie: MovieDatabaseEntity) = movieRepository.deleteFromDataBase(movie)
}