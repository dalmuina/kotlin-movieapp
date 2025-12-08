package com.dalmuina.features.movies.ui.movieDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dalmuina.features.movies.ui.model.MovieUi
import com.dalmuina.features.movies.ui.theme.GeneralPadding.PaddingL
import com.dalmuina.features.movies.ui.theme.GeneralPadding.PaddingS
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MovieDetailScreen(
        uiState = uiState,
        onBack = {}
    )
}

@Composable
fun MovieDetailScreen(
    uiState: MovieDetailUiState,
    onBack: () -> Unit
) {
    when {
        uiState.isLoading -> Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        uiState.error != null -> Text(text = uiState.error)

        else -> uiState.movie?.let {
            MovieDetailContent(it)
        } ?: Text("Sin datos disponibles")
    }
}

@Composable
fun MovieDetailContent(
    movie: MovieUi
) {
    Column(Modifier.padding(PaddingL)) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(PaddingS))
        Text(text = movie.overview)
    }
}