package com.joseph.moviesmultiplatformapp.data.mappers

import com.joseph.moviesmultiplatformapp.data.remote.models.MovieRemote
import com.joseph.moviesmultiplatformapp.domain.utils.Mapper
import com.joseph.moviesmultiplatformapp.domain.models.Movie

internal class MovieRemoteToMovieMapper : Mapper<MovieRemote, Movie> {

    private companion object {
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500/"
    }

    override fun map(from: MovieRemote): Movie = from.run {
        Movie(
            id = id,
            overview = overview,
            title = title,
            posterImageUrl = "${BASE_POSTER_URL}$posterImage",
            releaseDate = releaseDate ?: String()
        )
    }
}