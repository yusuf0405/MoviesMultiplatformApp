package com.joseph.moviesmultiplatformapp.data.remote.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
internal data class MovieRemote(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterImage: String,
    @SerialName("release_date") val releaseDate: String? = null,
)
