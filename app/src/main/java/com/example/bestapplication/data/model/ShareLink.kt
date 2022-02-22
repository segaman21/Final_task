package com.example.bestapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ShareLink(
    @SerialName("link") val shareLink: String
) : Parcelable

@Serializable
data class ShareLinkRu(
    @SerialName("RU") val link: ShareLink
)

@Serializable
data class ResultShareLink(
    @SerialName("result") val resultLink: ShareLinkRu
)