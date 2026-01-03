package com.innovation.movies.task.feature.movies.presentation.ui.movies.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innovation.movies.task.core.localization.R
import com.innovation.movies.task.core.ui.sharedcomponent.images.InnovationRemoteRoundedImage
import com.innovation.movies.task.feature.movies.presentation.model.MovieUI

@Composable
internal fun MovieItem(
    movie: MovieUI,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieStatusIndicator(adult = movie.adult)
            Spacer(modifier = Modifier.width(8.dp))
            MovieAvatar(image = movie.posterImage)
            Spacer(modifier = Modifier.width(12.dp))
            MovieInfo(
                title = movie.title,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount,
                releaseDate = movie.releaseDate,
                adult = movie.adult
            )
        }
    }
}

@Composable
private fun MovieStatusIndicator(
    adult: Boolean
) {
    val indicatorColor = if (adult) {
        Color.Yellow
    } else {
        Color.Green
    }
    Box(
        modifier = Modifier
            .width(4.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(indicatorColor)
    )
}

@Composable
private fun MovieAvatar(image: String) {
    InnovationRemoteRoundedImage(
        image = image,
        modifier = Modifier.size(48.dp)
    )
}

@Composable
private fun MovieInfo(
    title: String,
    voteAverage: Double,
    voteCount: Int,
    releaseDate: String,
    adult: Boolean
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            MovieCertification(adult = adult)
        }

        Spacer(modifier = Modifier.height(4.dp))

        MovieRating(rating = voteAverage)

        Spacer(modifier = Modifier.height(4.dp))

        MovieMeta(
            voteCount = voteCount,
            releaseYear = releaseDate.take(4)
        )
    }
}

@Composable
private fun MovieCertification(
    adult: Boolean
) {
    Text(
        text = if (adult) stringResource(R.string.movie_adult_label) else stringResource(R.string.movie_not_adult_label),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun MovieRating(
    rating: Double
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "⭐",
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "%.1f".format(rating),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun MovieMeta(
    voteCount: Int,
    releaseYear: String
) {
    Text(
        text = stringResource(
            id = R.string.movies_screen_item_movie_votes_and_year,
            voteCount,
            releaseYear
        ),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
