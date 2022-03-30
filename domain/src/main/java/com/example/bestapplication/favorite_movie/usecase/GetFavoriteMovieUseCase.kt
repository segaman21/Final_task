package com.example.bestapplication.favorite_movie.usecase

import com.example.bestapplication.repository.FavoriteMovieRepository
import javax.inject.Inject

class GetFavoriteMovieUseCase @Inject constructor(private val movieRepository: FavoriteMovieRepository) {

    suspend fun invoke() = movieRepository.getFavoriteMovie()

}