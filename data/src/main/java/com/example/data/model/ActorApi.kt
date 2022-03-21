package com.example.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Actor(
    @Json(name ="id") val id: Int,
    @Json(name ="name") val name: String,
    @Json(name ="profile_path") val picture: String?
)

@JsonClass(generateAdapter = true)
data class ActorList(
    @Json(name ="cast") val actors: List<Actor>
)