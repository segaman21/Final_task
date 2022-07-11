package com.example.bestapplication.favorite_movie.usecase

import com.example.bestapplication.repository.FavoriteMovieRepository

class GetFavoriteMovieUseCase (private val movieRepository: FavoriteMovieRepository) {

    suspend fun invoke() = movieRepository.getFavoriteMovie()

    suspend fun findMovie(name:String) = movieRepository.getFindMovie(name)

}