package com.example.bestapplication

import com.example.bestapplication.data.database.MovieEntity
import com.example.bestapplication.data.model.MovieFull
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Mapper {
    @ExperimentalSerializationApi
    fun mapMoviesListToDb(movieDetails: MovieFull): MovieEntity {

        val genres = Json.encodeToString(movieDetails.genres)
        return MovieEntity(
            movieDetails.id,
            movieDetails.poster,
            movieDetails.title,
            movieDetails.ratings,
            genres,
            movieDetails.runtime
        )
    }
}