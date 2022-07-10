package com.example.bestapplication

import com.example.bestapplication.model.ActorApi
import com.example.bestapplication.model.GenreApi
import com.example.bestapplication.model.MovieFullApi
import com.example.bestapplication.model.MoviePreviewApi
import com.example.bestapplication.database.MovieDatabaseEntity
import com.example.bestapplication.favorite_movie.entity.FavoriteMovie
import com.example.bestapplication.movie_details.entity.Actor
import com.example.bestapplication.movie_details.entity.MovieFull
import com.example.bestapplication.movie_list.entity.Genre
import com.example.bestapplication.movie_list.entity.MoviePreview
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object Mapper {


    @ExperimentalSerializationApi
    fun mapMoviesListToDb(movie: MovieFullApi): MovieDatabaseEntity {

        val genres = Json.encodeToString(movie.genres)
        return MovieDatabaseEntity(
            movie.id,
            movie.poster,
            movie.title,
            movie.ratings,
            genres,
            movie.runtime
        )
    }

    fun mapMoviesInDb(movie: FavoriteMovie): MovieDatabaseEntity {

        return MovieDatabaseEntity(
            movie.id,
            movie.poster,
            movie.title,
            movie.ratings,
            movie.genres,
            movie.runtime
        )
    }

    fun mapMoviesFromDb(movie: MovieDatabaseEntity): FavoriteMovie {

        return FavoriteMovie(
            movie.id,
            movie.poster,
            movie.title,
            movie.ratings,
            movie.genres,
            movie.runtime
        )

    }

    fun mapFindMoviesFromDb(movie: List<MovieDatabaseEntity>): List<FavoriteMovie> {

        return movie.map {
            FavoriteMovie(
                it.id,
                it.poster,
                it.title,
                it.ratings,
                it.genres,
                it.runtime
            )
        }
    }

    fun mapMoviePreview(movie: MoviePreviewApi): MoviePreview {

        return MoviePreview(
            movie.id,
            movie.title,
            movie.poster,
            movie.ratings,
            movie.numberOfRatings,
            movie.genres,
            movie.minimumAge
        )
    }


    fun mapGenre(movie: GenreApi): Genre {

        return Genre(
            movie.id,
            movie.name
        )
    }

    fun mapFullMovie(movie: MovieFullApi): MovieFull {

        return MovieFull(
            movie.id,
            movie.title,
            movie.overview,
            movie.backdrop,
            movie.ratings,
            movie.numberOfRatings,
            mapGenreInFullMovie(movie.genres),
            movie.minimumAge,
            movie.runtime,
            movie.poster,
        )
    }

    private fun mapGenreInFullMovie(movie: List<GenreApi>): List<Genre> {

        return movie.map {
            Genre(
                it.id,
                it.name
            )
        }
    }

    fun mapActors(movie: ActorApi): Actor {

        return Actor(
            movie.id,
            movie.name,
            movie.picture
        )
    }

}
