package com.joseph.moviesmultiplatformapp.di

import com.joseph.moviesmultiplatformapp.data.mappers.MovieRemoteToMovieMapper
import com.joseph.moviesmultiplatformapp.data.remote.api.MovieService
import com.joseph.moviesmultiplatformapp.data.remote.data_source.MovieRemoteDataSource
import com.joseph.moviesmultiplatformapp.data.remote.data_source.MovieRemoteDataSourceImpl
import com.joseph.moviesmultiplatformapp.data.remote.models.MovieRemote
import com.joseph.moviesmultiplatformapp.data.repository.MovieRepositoryImpl
import com.joseph.moviesmultiplatformapp.data.utils.provideDispatcher
import com.joseph.moviesmultiplatformapp.domain.models.Movie
import com.joseph.moviesmultiplatformapp.domain.repository.MovieRepository
import com.joseph.moviesmultiplatformapp.domain.use_cases.FetchAllMoviesUseCase
import com.joseph.moviesmultiplatformapp.domain.use_cases.FetchMovieUseCase
import com.joseph.moviesmultiplatformapp.domain.utils.Mapper
import org.koin.dsl.module


private val dataModule = module {

    factory<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl(
            service = get(),
            dispatcher = get()
        )
    }
    factory { MovieService() }
    factory<Mapper<MovieRemote, Movie>> { MovieRemoteToMovieMapper() }
}

private val utilsModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            movieRemoteDataSource = get(),
            movieRemoteToMovieMapper = get()
        )
    }

    factory { FetchAllMoviesUseCase() }
    factory { FetchMovieUseCase() }
}

private val sharedModules = listOf(dataModule, domainModule, utilsModule)

fun fetchSharedModules() = sharedModules