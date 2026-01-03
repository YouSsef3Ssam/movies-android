package com.innovation.movies.task.app.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.innovation.movies.task.core.ui.foundation.theme.InnovationTheme
import com.innovation.movies.task.feature.main.presentation.ui.main.MainScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InnovationTheme {
                MainScreen()
            }
        }
    }
}