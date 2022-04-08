package com.example.bestapplication.movie_list.usecase

import com.example.bestapplication.repository.MovieRepository

class GetMovieListUseCase (private val movieRepository: MovieRepository) {

    suspend fun invoke() = movieRepository.loadMovies()
}