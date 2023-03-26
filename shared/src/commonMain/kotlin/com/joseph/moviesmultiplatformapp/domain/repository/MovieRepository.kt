package com.joseph.moviesmultiplatformapp.domain.repository

import com.joseph.moviesmultiplatformapp.domain.models.Movie

internal interface MovieRepository {

    suspend fun fetchAllMovies(page: Int): List<Movie>

    suspend fun fetchMovie(movieId: Int): Movie
}