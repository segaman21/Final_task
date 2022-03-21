package com.example.bestapplication

import com.example.bestapplication.repositories.MovieRepository


class GetMovieListUseCase (private val movieRepository: MovieRepository) {

    suspend fun invoke() = movieRepository.loadMovies()
}