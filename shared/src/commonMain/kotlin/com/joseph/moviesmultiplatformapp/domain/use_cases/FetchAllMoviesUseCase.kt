package com.joseph.moviesmultiplatformapp.domain.use_cases

import com.joseph.moviesmultiplatformapp.domain.models.Movie
import com.joseph.moviesmultiplatformapp.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class FetchAllMoviesUseCase : KoinComponent {

    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend fun invoke(page: Int): List<Movie> {
        return repository.fetchAllMovies(page)
    }
}