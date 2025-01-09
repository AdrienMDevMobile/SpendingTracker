package com.micheldr.spendingtracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    secondary = Color.Black,
    secondaryVariant = Color.DarkGray,
    surface = Color.Gray,
)

private val LightColorPalette = lightColors(
    secondary = Color.White,
    secondaryVariant = Color.LightGray,
    surface = Color.Gray,
)

@Composable
fun SpendingTrackerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

val Colors.onBackgroundDisabled: Color
    get() = disabled

val Colors.highlightBackGround: Color
    get() = highlight