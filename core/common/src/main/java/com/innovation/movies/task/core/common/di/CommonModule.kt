package com.innovation.movies.task.core.common.di

import com.innovation.movies.task.core.common.data.DispatcherQualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule =
    module {
        /** Json **/
        single<Json> {
            Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        }

        /** Dispatchers **/
        single<CoroutineDispatcher>(named(DispatcherQualifier.IO)) { Dispatchers.IO }
        single<CoroutineDispatcher>(named(DispatcherQualifier.Main)) { Dispatchers.Main }
        single<CoroutineDispatcher>(named(DispatcherQualifier.Default)) { Dispatchers.Default }
    }
