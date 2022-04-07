package com.example.bestapplication.favorite_movie.usecase

import com.example.bestapplication.favorite_movie.entity.FavoriteMovie
import com.example.bestapplication.repository.FavoriteMovieRepository
import javax.inject.Inject

class DeleteFromDataBase @Inject constructor(private val movieRepository: FavoriteMovieRepository) {

    suspend fun invoke(movie: FavoriteMovie) = movieRepository.deleteFromDataBase(movie)
}