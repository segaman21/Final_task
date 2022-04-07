package com.example.bestapplication.favorite_movie.usecase

import com.example.bestapplication.repository.FavoriteMovieRepository
import javax.inject.Inject

class CheckMovieInDatabaseUseCase @Inject constructor(private val movieRepository: FavoriteMovieRepository) {

    suspend fun invoke(movieId: Int) = movieRepository.checkInDataBase(movieId)
}