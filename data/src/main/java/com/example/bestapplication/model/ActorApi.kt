package com.example.bestapplication.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ActorApi(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val picture: String?
)

@Serializable
data class ActorListApi(
    @SerializedName("cast") val actors: List<ActorApi>
)