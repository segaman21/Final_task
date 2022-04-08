package com.example.bestapplication.movie_details.usecase

import com.example.bestapplication.repository.MovieRepository

class GetActorUseCase (private val movieRepository: MovieRepository) {

    suspend fun invoke(movieId: Int) = movieRepository.loadActors(movieId)
}