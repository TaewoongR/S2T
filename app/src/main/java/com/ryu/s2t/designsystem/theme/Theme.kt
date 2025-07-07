package com.ryu.s2t.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme: ColorScheme = lightColorScheme(
    primary = Green,
    primaryContainer = LightYellow,
    secondary = Purple,
    surface = White,
    surfaceContainerHighest = LightYellow,
    onSurface = Black,
    onSurfaceVariant = DarkGray,
    background = LightGray
)

private val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = Green,
    primaryContainer = LightYellow,
    secondary = Purple,
    surface = White,
    surfaceContainerHighest = LightYellow,
    onSurface = Black,
    onSurfaceVariant = DarkGray,
    background = LightGray
)

@Composable
fun RecordAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
