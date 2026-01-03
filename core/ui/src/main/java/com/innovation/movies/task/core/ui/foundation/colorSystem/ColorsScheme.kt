package com.innovation.movies.task.core.ui.foundation.colorSystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.innovation.movies.task.core.ui.foundation.colorSystem.palette.DarkColors
import com.innovation.movies.task.core.ui.foundation.colorSystem.palette.LightColors

internal val LightColorScheme = lightColorScheme(
    primary = LightColors.Primary,
    background = LightColors.White,
    surface = LightColors.Background,
)

internal val DarkColorScheme = darkColorScheme(
    primary = DarkColors.Primary,
    background = DarkColors.Black,
    surface = DarkColors.Background,
)