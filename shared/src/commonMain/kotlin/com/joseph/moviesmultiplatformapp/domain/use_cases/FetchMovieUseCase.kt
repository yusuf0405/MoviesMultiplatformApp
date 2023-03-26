package com.joseph.moviesmultiplatformapp.domain.use_cases

import com.joseph.moviesmultiplatformapp.domain.models.Movie
import com.joseph.moviesmultiplatformapp.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class FetchMovieUseCase : KoinComponent {

    private val repository: MovieRepository by inject()

    suspend operator fun invoke(movieId: Int): Movie {
        return repository.fetchMovie(movieId)
    }
}