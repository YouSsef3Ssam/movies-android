package com.innovation.movies.task.feature.movies.common.di

import androidx.room.Room
import com.innovation.movies.task.feature.movies.common.DATABASE_NAME
import com.innovation.movies.task.feature.movies.data.local.MoviesDatabase
import com.pluto.plugins.rooms.db.PlutoRoomsDBWatcher
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val databaseModule =
    module {
        single<MoviesDatabase> {
            Room.databaseBuilder(
                androidContext(),
                MoviesDatabase::class.java,
                DATABASE_NAME,
            )
                .fallbackToDestructiveMigration(true)
                .build()
                .apply { PlutoRoomsDBWatcher.watch(DATABASE_NAME, MoviesDatabase::class.java) }
        }
    }