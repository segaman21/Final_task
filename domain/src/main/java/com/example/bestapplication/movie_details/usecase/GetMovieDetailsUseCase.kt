package com.example.bestapplication.movie_details.usecase


import com.example.bestapplication.repository.MovieRepository

class GetMovieDetailsUseCase (private val movieRepository: MovieRepository) {

    suspend fun invoke(movieId: Int) = movieRepository.loadFullMovie(movieId)
}