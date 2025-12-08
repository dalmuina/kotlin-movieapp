package com.dalmuina.features.movies.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dalmuina.features.movies.ui.model.MovieUi

@Composable
fun MovieScreen(
    viewModel: MoviesViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    MoviesContent(
        uiState = uiState,
        onMovieClick = { viewModel.onMovieSelected(it) },
        modifier = modifier
    )
}

@Composable
fun MoviesContent(
    uiState: MoviesUiState,
    onMovieClick: (MovieUi) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> {
            Box(modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
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
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie, onClick = { onMovieClick(movie) })
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieUi,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(12.dp)) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier.size(90.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = movie.overview,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}