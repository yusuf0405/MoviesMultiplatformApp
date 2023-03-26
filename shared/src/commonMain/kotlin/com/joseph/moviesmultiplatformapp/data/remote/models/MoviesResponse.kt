package com.joseph.moviesmultiplatformapp.data.remote.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
internal data class MoviesResponse(
    @SerialName("results") val movies: List<MovieRemote>
)
