package com.example.bestapplication.movie_list.usecase


import com.example.bestapplication.repository.MovieRepository
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(private val movieRepository: MovieRepository){

    suspend fun invoke() = movieRepository.loadGenres()
}