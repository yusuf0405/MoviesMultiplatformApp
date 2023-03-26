package com.joseph.moviesmultiplatformapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joseph.moviesmultiplatformapp.android.common.Detail
import com.joseph.moviesmultiplatformapp.android.common.Detail.MOVIE_ID
import com.joseph.moviesmultiplatformapp.android.common.Home
import com.joseph.moviesmultiplatformapp.android.common.MovieAppBar
import com.joseph.moviesmultiplatformapp.android.common.movieDestinations
import com.joseph.moviesmultiplatformapp.android.screens.details.DetailScreen
import com.joseph.moviesmultiplatformapp.android.screens.details.DetailViewModel
import com.joseph.moviesmultiplatformapp.android.screens.home.HomeScreen
import com.joseph.moviesmultiplatformapp.android.screens.home.HomeScreenState
import com.joseph.moviesmultiplatformapp.android.screens.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {

    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isSystemDark = isSystemInDarkTheme()
    val scaffoldState = rememberScaffoldState()


    val statusBarColor = if (isSystemDark) MaterialTheme.colors.primaryVariant
    else Color.Transparent
    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor, darkIcons = !isSystemDark)
    }
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = movieDestinations.find { movieDestination ->
        backStackEntry?.destination?.route == movieDestination.route ||
                backStackEntry?.destination?.route == movieDestination.routeWithArgs
    } ?: Home


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MovieAppBar(
                canNavigationBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs,
        ) {
            composable(route = Home.routeWithArgs) {
                val viewModel: HomeViewModel = koinViewModel()
                HomeScreen(
                    uiState = viewModel.uiState,
                    loadNextMovies = { isForceLoad ->
                        viewModel.startLoadMovies(forceLoad = isForceLoad)
                    },
                    navigateToDetail = { movie ->
                        navController.navigate(
                            "${Detail.route}/${movie.id}"
                        )
                    }
                )
            }
            composable(route = Detail.routeWithArgs) {
                val movieId = it.arguments?.getString(MOVIE_ID) ?: "0"
                val viewModel: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(movieId.toInt()) }
                )
                DetailScreen(uiState = viewModel.uiState)
            }
        }
    }
}