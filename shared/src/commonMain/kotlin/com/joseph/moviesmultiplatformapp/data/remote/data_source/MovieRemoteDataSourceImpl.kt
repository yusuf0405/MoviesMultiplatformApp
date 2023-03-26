package com.joseph.moviesmultiplatformapp.data.remote.data_source

import com.joseph.moviesmultiplatformapp.data.remote.api.MovieService
import com.joseph.moviesmultiplatformapp.data.remote.models.MovieRemote
import com.joseph.moviesmultiplatformapp.data.utils.Dispatcher
import kotlinx.coroutines.withContext

internal class MovieRemoteDataSourceImpl(
    private val service: MovieService,
    private val dispatcher: Dispatcher,
) : MovieRemoteDataSource {

    override suspend fun fetchAllMovies(page: Int): List<MovieRemote> = withContext(dispatcher.io) {
        return@withContext service.fetchAllMovies(page).movies
    }

    override suspend fun fetchMovie(movieId: Int): MovieRemote = withContext(dispatcher.io) {
        return@withContext service.fetchMovie(movieId)
    }
}