package com.joseph.moviesmultiplatformapp.android.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.joseph.moviesmultiplatformapp.android.APP_BAR_HIGH


@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    currentScreen: Destinations,
    onNavigateBack: () -> Unit
) {
    val currentDestination = navHostController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    val canNavigationBack = navHostController.previousBackStackEntry != null


    Surface(
        modifier = fetchAppBarModifier(currentDestination)
            .padding(top = fetchAppBarPaddingTop(currentDestination))
            .fillMaxWidth()
            .height(APP_BAR_HIGH),
        elevation = fetchAppBarElevation(currentDestination),
        color = fetchAppBarColor(currentDestination)
    ) {
        Row(
            modifier = modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = canNavigationBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = modifier.width(24.dp))
            }
            Text(
                text = currentScreen.title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(12.dp),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

private fun fetchAppBarElevation(currentDestinationRoute: String?): Dp {
    return when (currentDestinationRoute) {
        Detail.routeWithArgs -> 0.dp
        else -> 4.dp
    }
}

@Composable
private fun fetchAppBarColor(currentDestinationRoute: String?): Color {
    return when (currentDestinationRoute) {
        Detail.routeWithArgs -> Color.Transparent
        else -> MaterialTheme.colors.primary
    }
}

private fun fetchAppBarModifier(currentDestinationRoute: String?): Modifier {
    return when (currentDestinationRoute) {
        Detail.routeWithArgs -> Modifier
        else -> Modifier.statusBarsPadding()
    }
}

@Composable
private fun fetchAppBarPaddingTop(currentDestinationRoute: String?): Dp {
    return when (currentDestinationRoute) {
        Detail.routeWithArgs -> 40.dp
        else -> 0.dp
    }
}