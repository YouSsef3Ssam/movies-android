package com.innovation.movies.task.feature.movies.presentation.ui.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.innovation.movies.task.core.localization.R
import com.innovation.movies.task.core.ui.sharedcomponent.images.InnovationRemoteImage
import com.innovation.movies.task.feature.movies.presentation.model.MovieUI
import com.innovation.movies.task.feature.movies.presentation.ui.movieDetails.manipulator.MovieDetailsState
import com.innovation.movies.task.feature.movies.presentation.ui.movieDetails.manipulator.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MovieDetailsRoute(viewModel: MovieDetailsViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    MovieDetailsScreen(state = state)
}

@Composable
private fun MovieDetailsScreen(state: MovieDetailsState) {
    state.movie?.let {
        MovieDetailsContent(movie = state.movie)
    }
}

@Composable
private fun MovieDetailsContent(movie: MovieUI) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
    ) {
        MoviePoster(movie.posterImage)
        MovieTitle(movie.title)
        MovieDetailsInfo(movie)
        MovieOverview(movie.overview)
    }
}

@Composable
private fun MoviePoster(imageUrl: String) {
    InnovationRemoteImage(
        image = imageUrl,
        modifier =
            Modifier
                .fillMaxWidth()
                .height(400.dp),
        contentScale = ContentScale.FillBounds,
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun MovieTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
    )

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun MovieDetailsInfo(movie: MovieUI) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        MovieInfoRow(
            label = stringResource(R.string.movie_details_screen_adult_label),
            value =
                if (movie.adult) {
                    stringResource(R.string.movie_details_screen_movie_type_adult)
                } else {
                    stringResource(R.string.movie_details_screen_movie_type_not_adult)
                },
        )
        MovieInfoRow(
            label = stringResource(R.string.movie_details_screen_rate_label),
            value = "${"%.1f".format(
                movie.voteAverage,
            )} (${movie.voteCount} ${stringResource(R.string.movie_details_screen_votes_label)})",
        )
        MovieInfoRow(
            label = stringResource(R.string.movie_details_screen_language_label),
            value = movie.originalLanguage.uppercase(),
        )
        MovieInfoRow(
            label = stringResource(R.string.movie_details_screen_release_date_label),
            value = movie.releaseDate,
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun MovieInfoRow(
    label: String,
    value: String,
) {
    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun MovieOverview(overview: String) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.movie_details_screen_overview_label),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = overview,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp,
        )
    }

    Spacer(modifier = Modifier.height(24.dp))
}
