package com.joseph.moviesmultiplatformapp.data.remote.api

import com.joseph.moviesmultiplatformapp.data.remote.models.MovieRemote
import com.joseph.moviesmultiplatformapp.data.remote.models.MoviesResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

internal class MovieService : KtorApi() {

    suspend fun fetchAllMovies(page: Int = 1): MoviesResponse = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun fetchMovie(movieId: Int): MovieRemote = client.get {
        pathUrl("movie/${movieId}")
    }.body()
}