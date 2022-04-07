package com.example.bestapplication.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePreviewApi(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("vote_average") val ratings: Float,
    @SerializedName("vote_count") val numberOfRatings: Int,
    @SerializedName("genre_ids") val genres: List<Int>,
    @SerializedName("adult") val minimumAge: Boolean
)

@Serializable
data class MovieListApi(
    @SerializedName("results") val results: List<MoviePreviewApi>
)

@Serializable
data class MovieFullApi(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val backdrop: String,
    @SerializedName("vote_average") val ratings: Float,
    @SerializedName("vote_count") val numberOfRatings: Int,
    @SerializedName("genres") val genres: List<GenreApi>,
    @SerializedName("adult") val minimumAge: Boolean,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("poster_path") val poster: String
)