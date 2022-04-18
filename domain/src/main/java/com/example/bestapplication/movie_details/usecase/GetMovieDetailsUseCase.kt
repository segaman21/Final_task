package com.example.bestapplication.movie_details.usecase


import com.example.bestapplication.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun invoke(movieId: Int) = movieRepository.loadFullMovie(movieId)
}