package com.innovation.movies.task.core.navigation.entry.movieDetails

import com.innovation.movies.task.core.navigation.entry.movieDetails.args.MovieArgs
import com.innovation.movies.task.core.navigation.entry.movieDetails.navTypes.MovieArgsNavType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
class MovieDetailsEntry(
    val id: String,
    val movie: MovieArgs? = null,
) {
    companion object Companion {
        val typeMap
            get() = mapOf(
                typeOf<MovieArgs>() to MovieArgsNavType,
                typeOf<MovieArgs?>() to MovieArgsNavType,
            )
    }
}