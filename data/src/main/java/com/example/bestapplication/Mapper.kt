package com.example.bestapplication

import com.example.bestapplication.model.ActorApi
import com.example.bestapplication.model.GenreApi
import com.example.bestapplication.model.MovieFullApi
import com.example.bestapplication.model.MoviePreviewApi
import com.example.bestapplication.database.MovieEntity
import com.example.bestapplication.favorite_movie.entity.MovieDatabaseEntity
import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object Mapper {


    @ExperimentalSerializationApi
    fun mapMoviesListToDb(movie: MovieFullApi): MovieEntity {

        val genres = Json.encodeToString(movie.genres)
        return MovieEntity(
            movie.id,
            movie.poster,
            movie.title,
            movie.ratings,
            genres,
            movie.runtime
        )
    }

    fun mapMoviesInDb(movie: MovieDatabaseEntity): MovieEntity {

        return MovieEntity(
            movie.id,
            movie.poster,
            movie.title,
            movie.ratings,
            movie.genres,
            movie.runtime
        )
    }

    fun mapMoviesFromDb(movie: List<MovieEntity>): List<MovieDatabaseEntity> {

        return movie.map {
            MovieDatabaseEntity(
                it.id,
                it.poster,
                it.title,
                it.ratings,
                it.genres,
                it.runtime
            )
        }
    }

    fun mapMoviePreview(movie: List<MoviePreviewApi>): List<MoviePreview> {

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

    fun mapGenre(movie: List<GenreApi>): List<Genre> {

        return movie.map {
            Genre(
                it.id,
                it.name
            )
        }
    }

    fun mapFullMovie(movie: MovieFullApi): MovieFull {

        return MovieFull(
            movie.id,
            movie.title,
            movie.overview,
            movie.backdrop,
            movie.ratings,
            movie.numberOfRatings,
            mapGenre(movie.genres),
            movie.minimumAge,
            movie.runtime,
            movie.poster,
        )
    }


    fun mapActors(movie: List<ActorApi>): List<Actor> {

        return movie.map {
            Actor(
                it.id,
                it.name,
                it.picture
            )
        }
    }
}
