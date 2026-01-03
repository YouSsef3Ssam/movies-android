package com.innovation.movies.task.feature.movies.data.local.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

internal object GenreIdsConverter {
    @TypeConverter
    fun fromGenreIds(value: List<Int>): String = Json.encodeToString(value)

    @TypeConverter
    fun toGenreIds(value: String): List<Int> = Json.decodeFromString(value)
}
