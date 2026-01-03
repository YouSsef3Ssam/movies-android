package com.innovation.movies.task.feature.movies.domain.mapper

import com.innovation.movies.task.feature.movies.common.POSTER_BASE_URL
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.domain.model.DomainMovie

internal fun MovieEntity.toDomain(): DomainMovie =
    DomainMovie(
        id = id,
        title = title,
        posterImage = "$POSTER_BASE_URL$posterPath",
        overview = overview,
        adult = adult,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originalLanguage = originalLanguage,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalTitle = originalTitle,
        popularity = popularity,
        video = video,
    )
