package com.innovation.movies.task.app.presentation.initializer

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.innovation.movies.task.app.BuildConfig
import com.innovation.movies.task.feature.main.common.di.mainModule
import com.pluto.Pluto
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.logger.PlutoTimberTree
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class AppInitializers : Initializer<Unit> {
    override fun create(context: Context) {
        installKoin(context = context)
        installPluto(application = context as Application)
        installTimber()
    }

    private fun installKoin(context: Context) {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            androidContext(context)
            modules(mainModule)
        }
    }

    private fun installPluto(application: Application) {
        Pluto
            .Installer(application)
            .addPlugin(PlutoNetworkPlugin())
            .addPlugin(PlutoLoggerPlugin())
            .addPlugin(PlutoRoomsDatabasePlugin())
            .install()
    }

    private fun installTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(PlutoTimberTree())
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
