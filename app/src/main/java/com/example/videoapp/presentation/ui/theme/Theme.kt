package com.example.videoapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,
    background = Black,
    onBackground = White,
    error = Red
)

private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    background = White,
    onBackground = Black,
    error = Red
)

@Composable
fun VideoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = colors,
        content = content
    )

    val systemUiController = rememberSystemUiController()

    SideEffect {
        if (darkTheme) {
            systemUiController.setSystemBarsColor(
                color = Color.Black,
                darkIcons = false
            )
        } else {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }
    }
}