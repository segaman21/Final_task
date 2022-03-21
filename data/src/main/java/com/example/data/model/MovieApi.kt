package com.example.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviePreviewApi(
        @Json(name ="id") val id: Int,
        @Json(name ="original_title") val title: String,
        @Json(name ="poster_path") val poster: String,
        @Json(name ="vote_average") val ratings: Float,
        @Json(name ="vote_count") val numberOfRatings: Int,
        @Json(name ="genre_ids") val genres: List<Int>,
        @Json(name ="adult") val minimumAge: Boolean
)

@JsonClass(generateAdapter = true)
data class MovieList(
        @Json(name ="results") val results: List<MoviePreviewApi>
)
@JsonClass(generateAdapter = true)
data class MovieFullApi(
        @Json(name ="id") val id: Int,
        @Json(name ="original_title") val title: String,
        @Json(name ="overview") val overview: String,
        @Json(name ="backdrop_path") val backdrop: String,
        @Json(name ="vote_average") val ratings: Float,
        @Json(name ="vote_count") val numberOfRatings: Int,
        @Json(name ="genres") val genres: List<Genre>,
        @Json(name ="adult") val minimumAge: Boolean,
        @Json(name ="runtime") val runtime: Int,
        @Json(name ="poster_path") val poster: String
)