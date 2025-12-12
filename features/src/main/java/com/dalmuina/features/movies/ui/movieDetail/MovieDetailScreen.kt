package com.dalmuina.features.movies.ui.movieDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.dalmuina.designsystem.components.AnimatedScreen
import com.dalmuina.designsystem.tokens.Spacing.l
import com.dalmuina.designsystem.tokens.Spacing.s
import com.dalmuina.features.movies.ui.model.MovieUi
import com.dalmuina.features.movies.ui.movies.RatingChip
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailRoute(
    movieId: Int,
    onBack: () -> Unit,
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    MovieDetailScreen(
        uiState = uiState,
        onBack = onBack
    )
}

@Composable
fun MovieDetailScreen(
    uiState: MovieDetailUiState,
    onBack: () -> Unit
) {
    AnimatedScreen {
        Scaffold(
            topBar = {
                MovieDetailTopBar(
                    title = uiState.movie?.title ?: "Movie Detail",
                    onBack = onBack
                )
            }
        ) { padding ->
            when {
                uiState.isLoading -> Box(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

                uiState.error != null -> Box(
                    Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.error)
                }

                uiState.movie != null -> MovieDetailContent(
                    movie = uiState.movie,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailTopBar(
    title: String,
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun MovieDetailContent(
    movie: MovieUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(l)
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(l))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(s))

        RatingChip(rating = movie.rating)

        Spacer(Modifier.height(l))

        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}