package com.joseph.moviesmultiplatformapp.android.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.moviesmultiplatformapp.domain.models.Movie
import com.joseph.moviesmultiplatformapp.domain.use_cases.FetchAllMoviesUseCase
import kotlinx.coroutines.launch

data class HomeScreenState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val movies: List<Movie> = listOf(),
    val errorMessage: String = String(),
    val loadFinished: Boolean = false
)

class HomeViewModel(
    private val fetchAllMoviesUseCase: FetchAllMoviesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    init {
        startLoadMovies()
    }

    fun startLoadMovies(forceLoad: Boolean = false) {
        if (uiState.loading) return
        if (forceLoad) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            runCatching {
                val resultMovies = fetchAllMoviesUseCase.invoke(currentPage)
                if (currentPage == 1) resultMovies else uiState.movies + resultMovies
            }.onSuccess { movies ->
                currentPage += 1
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = movies.isEmpty(),
                    movies = movies
                )
            }.onFailure {
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = it.localizedMessage ?: String()
                )
            }
        }
    }
}