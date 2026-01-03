package com.innovation.movies.task.feature.movies.data.mappers

import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.data.remote.dto.MovieResponseDto

internal fun MovieResponseDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = id.orEmpty(),
        title = title.orEmpty(),
        posterPath = posterPath.orEmpty(),
        overview = overview.orEmpty(),
        adult = adult ?: false,
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage ?: 1.0,
        voteCount = voteCount ?: 1,
        originalLanguage = originalLanguage.orEmpty(),
    )
}
