package com.joseph.moviesmultiplatformapp.data.repository

import com.joseph.moviesmultiplatformapp.data.remote.data_source.MovieRemoteDataSource
import com.joseph.moviesmultiplatformapp.data.remote.models.MovieRemote
import com.joseph.moviesmultiplatformapp.domain.models.Movie
import com.joseph.moviesmultiplatformapp.domain.repository.MovieRepository
import com.joseph.moviesmultiplatformapp.domain.utils.Mapper

internal class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieRemoteToMovieMapper: Mapper<MovieRemote, Movie>
) : MovieRepository {

    override suspend fun fetchAllMovies(page: Int): List<Movie> {
        val moviesRemote = movieRemoteDataSource.fetchAllMovies(page)
        return moviesRemote.map(movieRemoteToMovieMapper::map)
    }

    override suspend fun fetchMovie(movieId: Int): Movie {
        val movieRemote = movieRemoteDataSource.fetchMovie(movieId)
        return movieRemoteToMovieMapper.map(movieRemote)
    }
}