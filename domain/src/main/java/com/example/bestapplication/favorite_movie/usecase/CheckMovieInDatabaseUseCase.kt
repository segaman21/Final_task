package com.example.bestapplication.favorite_movie.usecase

import com.example.bestapplication.repository.FavoriteMovieRepository

class CheckMovieInDatabaseUseCase (private val movieRepository: FavoriteMovieRepository) {

    suspend fun invoke(movieId: Int) = movieRepository.checkInDataBase(movieId)
}