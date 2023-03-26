package com.joseph.moviesmultiplatformapp.android.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.moviesmultiplatformapp.domain.models.Movie
import com.joseph.moviesmultiplatformapp.domain.use_cases.FetchMovieUseCase
import kotlinx.coroutines.launch

data class DetailScreenState(
    val loading: Boolean = false,
    val movie: Movie? = null,
    val errorMassage: String = String()
)

class DetailViewModel(
    private val fetchMovieUseCase: FetchMovieUseCase,
    private val movieId: Int
) : ViewModel() {

    var uiState by mutableStateOf(DetailScreenState())

    init {
        loadMovie(movieId)
    }

    private fun loadMovie(movieId: Int) = viewModelScope.launch {
        uiState = uiState.copy(loading = true)
        runCatching {
            fetchMovieUseCase(movieId = movieId)
        }.onSuccess { movie ->
            uiState = uiState.copy(loading = false, movie = movie)
        }.onFailure {
            uiState = uiState.copy(loading = false, errorMassage = "${it.message}")
        }
    }

}