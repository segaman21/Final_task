package com.example.bestapplication.movie_list.usecase

import com.example.bestapplication.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun invoke() = movieRepository.loadMovies()
}