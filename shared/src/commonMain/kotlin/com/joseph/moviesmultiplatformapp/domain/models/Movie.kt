package com.joseph.moviesmultiplatformapp.domain.models

@kotlinx.serialization.Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterImageUrl: String,
    val releaseDate: String,
)