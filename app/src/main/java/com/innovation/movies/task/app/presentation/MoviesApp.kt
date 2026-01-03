package com.innovation.movies.task.app.presentation

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger
import com.innovation.movies.task.app.BuildConfig
import com.innovation.movies.task.app.common.DISK_CACHE_SIZE
import com.innovation.movies.task.app.common.IMAGE_CACHE_DIR
import com.innovation.movies.task.app.common.MEMORY_CACHE_SIZE

class MoviesApp : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader =
        ImageLoader
            .Builder(this)
            .memoryCache {
                MemoryCache
                    .Builder(this)
                    .maxSizePercent(MEMORY_CACHE_SIZE)
                    .build()
            }.diskCache {
                DiskCache
                    .Builder()
                    .directory(cacheDir.resolve(IMAGE_CACHE_DIR))
                    .maxSizePercent(DISK_CACHE_SIZE)
                    .build()
            }.crossfade(true)
            .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
            .build()
}
