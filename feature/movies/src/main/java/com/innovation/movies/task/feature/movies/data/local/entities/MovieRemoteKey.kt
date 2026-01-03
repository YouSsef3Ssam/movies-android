package com.innovation.movies.task.feature.movies.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.innovation.movies.task.feature.movies.common.MOVIES_REMOTE_KEYS_TABLE_NAME

@Entity(tableName = MOVIES_REMOTE_KEYS_TABLE_NAME)
internal data class MovieRemoteKey(
    @PrimaryKey
    val movieId: String,
    val prevPage: Int?,
    val nextPage: Int?,
)
