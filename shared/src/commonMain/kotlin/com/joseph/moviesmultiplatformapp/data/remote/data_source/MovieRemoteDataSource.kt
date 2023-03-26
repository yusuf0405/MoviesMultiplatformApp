package com.joseph.moviesmultiplatformapp.data.remote.data_source

import com.joseph.moviesmultiplatformapp.data.remote.models.MovieRemote

internal interface MovieRemoteDataSource {

    suspend fun fetchAllMovies(page: Int): List<MovieRemote>

    suspend fun fetchMovie(movieId: Int): MovieRemote
}