package com.example.bestapplication.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GenreApi(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

@Serializable
data class GenreListApi(
    @SerializedName("genres") val genres: List<GenreApi>
)