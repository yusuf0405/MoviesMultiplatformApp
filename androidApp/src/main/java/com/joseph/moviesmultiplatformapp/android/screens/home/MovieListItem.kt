package com.joseph.moviesmultiplatformapp.android.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.moviesmultiplatformapp.android.MovieApplicationTheme
import com.joseph.moviesmultiplatformapp.android.R
import com.joseph.moviesmultiplatformapp.domain.models.Movie

@Preview
@Composable
fun MovieListItemPreview() {
    MovieApplicationTheme {
        MovieListItem(
            movie = Movie.avatar,
            onMovieClick = {

            }
        )
    }

}

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {

    Card(
        modifier = modifier
            .height(280.dp)
            .clickable { onMovieClick(movie) },
        shape = RoundedCornerShape(8.dp)
    ) {

        Column {
            Box(
                modifier = modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = movie.posterImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 2.dp,
                                bottomEnd = 2.dp
                            )
                        )
                )

                Icon(
                    painter = painterResource(id = R.drawable.play_icon),
                    contentDescription = null,
                    modifier = modifier
                        .align(Alignment.Center)
                        .size(60.dp),
                    tint = Color.White.copy(alpha = 0.6f)
                )
            }

            Column(
                modifier = modifier.padding(10.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.caption,
                )
            }
        }

    }
}