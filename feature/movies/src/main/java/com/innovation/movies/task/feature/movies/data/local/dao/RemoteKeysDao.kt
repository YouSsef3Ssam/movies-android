package com.innovation.movies.task.feature.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.innovation.movies.task.feature.movies.common.MOVIES_REMOTE_KEYS_TABLE_NAME
import com.innovation.movies.task.feature.movies.data.local.entities.MovieRemoteKey

@Dao
internal interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<MovieRemoteKey>)

    @Query("SELECT * FROM $MOVIES_REMOTE_KEYS_TABLE_NAME WHERE movieId = :movieId")
    suspend fun remoteKeysById(movieId: String): MovieRemoteKey?

    @Query("DELETE FROM $MOVIES_REMOTE_KEYS_TABLE_NAME")
    suspend fun clearRemoteKeys()
}