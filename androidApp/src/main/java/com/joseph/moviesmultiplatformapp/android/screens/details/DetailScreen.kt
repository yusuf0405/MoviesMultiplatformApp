package com.joseph.moviesmultiplatformapp.android.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.moviesmultiplatformapp.android.APP_BAR_HIGH
import com.joseph.moviesmultiplatformapp.android.MovieApplicationTheme
import com.joseph.moviesmultiplatformapp.android.R
import com.joseph.moviesmultiplatformapp.android.Red
import com.joseph.moviesmultiplatformapp.domain.models.Movie

@Preview
@Composable
fun DetailScreenPreview() {
    MovieApplicationTheme {
        DetailScreen(
            uiState = DetailScreenState(
                movie = Movie.avatar
            )
        )
    }

}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenState
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val offsetY = statusBarHeight + APP_BAR_HIGH + 25.dp

    Box(
        modifier = modifier
            .fillMaxSize()
            .offset(y = (-offsetY)),
        contentAlignment = Alignment.Center
    ) {
        uiState.movie?.let { movie ->
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .background(color = MaterialTheme.colors.background)
            ) {
                Box(modifier = modifier) {
                    AsyncImage(
                        model = movie.posterImageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(470.dp)
                    )

                    val colors = arrayOf(
                        Pair(
                            0.4f,
                            Color.Transparent
                        ),
                        Pair(1f, MaterialTheme.colors.background),
                    )

                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .height(30.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colorStops = colors
                                )
                            )
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        modifier = modifier,
                        text = uiState.movie.title,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,

                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Button(
                        onClick = {

                        },
                        modifier = modifier
                            .padding(top = 8.dp)
                            .height(56.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Red
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_icon),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = modifier.width(4.dp))
                        Text(
                            text = "Start watching now", modifier = modifier.padding(start = 4.dp),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Spacer(modifier = modifier.padding(16.dp))
                    Text(
                        text = "Release in ${movie.releaseDate}".uppercase(),
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "${movie.overview}",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }

        if (uiState.loading) {
            Row(
                modifier = modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = Red
                )
            }
        }
    }

}