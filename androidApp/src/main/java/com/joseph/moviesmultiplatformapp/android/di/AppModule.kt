package com.joseph.moviesmultiplatformapp.android.di

import com.joseph.moviesmultiplatformapp.android.screens.details.DetailViewModel
import com.joseph.moviesmultiplatformapp.android.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params ->
        DetailViewModel(
            fetchMovieUseCase = get(),
            movieId = params.get()
        )
    }
}