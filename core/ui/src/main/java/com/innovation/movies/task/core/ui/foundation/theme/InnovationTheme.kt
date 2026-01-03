package com.innovation.movies.task.core.ui.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.innovation.movies.task.core.ui.foundation.colorSystem.DarkColorScheme
import com.innovation.movies.task.core.ui.foundation.colorSystem.LightColorScheme
import com.innovation.movies.task.core.ui.foundation.typography.Typography


@Composable
fun InnovationTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (isDark) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
