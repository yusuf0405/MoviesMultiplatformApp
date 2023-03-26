package com.joseph.moviesmultiplatformapp.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Home : Destinations {
    override val title: String
        get() = "Movies"

    override val route: String
        get() = "home"

    override val routeWithArgs: String
        get() = route
}

object Detail : Destinations {

    const val MOVIE_ID = "movieId"

    override val title: String
        get() = "Movies details"

    override val route: String
        get() = "detail"

    override val routeWithArgs: String
        get() = "$route/{$MOVIE_ID}"

    val arguments = listOf(
        navArgument(name = MOVIE_ID) { type = NavType.IntType }
    )
}

val movieDestinations = listOf(Home, Detail)
