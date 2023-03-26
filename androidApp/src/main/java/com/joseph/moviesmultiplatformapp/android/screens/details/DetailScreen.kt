package com.joseph.moviesmultiplatformapp.android.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.moviesmultiplatformapp.android.R
import com.joseph.moviesmultiplatformapp.android.Red

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenState
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        uiState.movie?.let { movie ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background)
            ) {
                AsyncImage(
                    model = movie.posterImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(380.dp)
                )
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(20.dp)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .padding(top = 8.dp)
                            .height(46.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Red
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_icon),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = modifier.width(4.dp))
                        Text(
                            text = "Start watching now", modifier = modifier.padding(start = 4.dp),
                            color = Color.White
                        )
                    }
                    Spacer(modifier = modifier.padding(16.dp))
                    Text(
                        text = "Release in ${movie.releaseDate}".uppercase(),
                        style = MaterialTheme.typography.overline
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