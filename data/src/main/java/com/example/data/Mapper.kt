package com.example.data

import com.example.bestapplication.models.MoviePreview
import com.example.data.database.MovieEntity
import com.example.data.model.MovieFullApi
import com.example.data.model.MoviePreviewApi


object Mapper {

    fun mapMoviesListToDb(movieDetails: MovieFullApi): MovieEntity {

        val genres = movieDetails.genres.toString()
        return MovieEntity(
            movieDetails.id,
            movieDetails.poster,
            movieDetails.title,
            movieDetails.ratings,
            genres,
            movieDetails.runtime
        )
    }

    fun mapFullMovie(movie: List<MoviePreviewApi>): List<MoviePreview> {

        return movie.map { movie ->
            MoviePreview(
                movie.id,
                movie.title,
                movie.poster,
                movie.ratings,
                movie.numberOfRatings,
                movie.genres,
                movie.minimumAge
            )
        }
    }
}