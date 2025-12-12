package com.dalmuina.features.movies.ui.movies

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.dalmuina.designsystem.components.AnimatedScreen
import com.dalmuina.designsystem.tokens.Dimens.posterSmallHeight
import com.dalmuina.designsystem.tokens.Dimens.posterSmallWidth
import com.dalmuina.designsystem.tokens.Spacing.l
import com.dalmuina.designsystem.tokens.Spacing.m
import com.dalmuina.designsystem.tokens.Spacing.s
import com.dalmuina.designsystem.tokens.Spacing.xs
import com.dalmuina.features.movies.ui.model.MovieUi
import com.dalmuina.features.movies.ui.preview.MoviePreviewData
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun MoviesRoute(
    onMovieSelected: (Int) -> Unit,
    viewModel: MoviesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MoviesScreen(
        uiState = uiState,
        onMovieClick = {onMovieSelected(it.id)}
    )
}

@Composable
fun MoviesScreen(
    uiState: MoviesUiState,
    onMovieClick: (MovieUi) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedScreen {
        when {
            uiState.isLoading -> {
                Box(
                    modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                ErrorScreen(uiState.error)
            }

            else -> {
                MovieList(
                    movies = uiState.movies,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(
    message: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}

@Composable
fun MovieList(
    movies: List<MovieUi>,
    onMovieClick: (MovieUi) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(l),
        verticalArrangement = Arrangement.spacedBy(m)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie, onClick = { onMovieClick(movie) })
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(m)) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .size(posterSmallWidth, posterSmallHeight)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(Modifier.width(m))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(xs))

                RatingChip(rating = movie.rating)

                Spacer(modifier = Modifier.height(s))

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
fun RatingChip(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier
    ) {
        Text(
            text = "⭐ %.1f".format(Locale.ROOT, rating),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = s, vertical = xs)
        )
    }
}

@Preview(showBackground = true, name = "MovieItem Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "MovieItem Dark")
@Composable
fun MovieItemPreview() {
    MaterialTheme {
        MovieItem(
            movie = MoviePreviewData.sampleMovie,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "MovieList Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "MovieList Dark")
@Composable
fun MovieListPreview() {
    MaterialTheme {
        MovieList(
            movies = MoviePreviewData.sampleMovies,
            onMovieClick = {}
        )
    }
}

@Preview(showBackground = true, name = "MoviesScreen Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "MoviesScreen Dark")
@Composable
fun MoviesScreenPreview() {
    MaterialTheme {
        MoviesScreen(
            uiState = MoviesUiState(
                isLoading = false,
                movies = MoviePreviewData.sampleMovies,
                error = null
            ),
            onMovieClick = {}
        )
    }
}
