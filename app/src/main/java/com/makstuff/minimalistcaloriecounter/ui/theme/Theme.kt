package com.makstuff.minimalistcaloriecounter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

private val LightColors = lightColorScheme(
    primary = LightStrong,
    onPrimary = LightBackground,
    primaryContainer = LightWeak,
    onPrimaryContainer = LightStrong,
    inversePrimary = LightWeak,
    secondary = LightText,
    onSecondary = LightBackground,
    secondaryContainer = LightShadow,
    onSecondaryContainer = LightText,
    tertiary = LightStrong,
    onTertiary = LightBackground,
    tertiaryContainer = LightWeak,
    onTertiaryContainer = LightText,
    background = LightBackground,
    onBackground = LightText,
    surface = LightBackground,
    onSurface = LightText,
    surfaceVariant = LightWeak,
    onSurfaceVariant = LightStrong,
    surfaceTint = LightText,
    inverseSurface = LightText,
    inverseOnSurface = LightBackground,
    error = LightWeak,
    onError = LightBackground,
    errorContainer = LightBackground,
    onErrorContainer = LightText,
    outline = LightWeak,
    outlineVariant = LightWeak,
    scrim = LightText,
    surfaceContainer = LightBackground,
    surfaceContainerHigh = LightBackground,
    surfaceContainerHighest = LightBackground,
)


private val DarkColors = darkColorScheme(
    primary = DarkStrong,
    onPrimary = DarkBackground,
    primaryContainer = DarkWeak,
    onPrimaryContainer = DarkStrong,
    inversePrimary = DarkWeak,
    secondary = DarkText,
    onSecondary = DarkBackground,
    secondaryContainer = DarkShadow,
    onSecondaryContainer = DarkText,
    tertiary = DarkStrong,
    onTertiary = DarkBackground,
    tertiaryContainer = DarkWeak,
    onTertiaryContainer = DarkText,
    background = DarkBackground,
    onBackground = DarkText,
    surface = DarkBackground,
    onSurface = DarkText,
    surfaceVariant = DarkWeak,
    onSurfaceVariant = DarkStrong,
    surfaceTint = DarkText,
    inverseSurface = DarkText,
    inverseOnSurface = DarkBackground,
    error = DarkWeak,
    onError = DarkBackground,
    errorContainer = DarkBackground,
    onErrorContainer = DarkText,
    outline = DarkWeak,
    outlineVariant = DarkWeak,
    scrim = DarkText,
    surfaceContainer = DarkBackground,
    surfaceContainerHigh = DarkBackground,
    surfaceContainerHighest = DarkBackground,
)

enum class AppTheme {
    MODE_DAY,
    MODE_NIGHT,
    MODE_AUTO;
}

data class DarkTheme(val isDark: Boolean = false)

val LocalTheme = compositionLocalOf { DarkTheme() }

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}
