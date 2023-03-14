package com.example.onlineshopsat.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = BackgroundColor,
    secondary = Color.Red,
    tertiary = Color.Red,
    background = BackgroundColor,
    onBackground = Color.Red,
    onTertiary = Color.Blue,
    surface = Color.Yellow,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    primaryContainer = Color.Magenta,
    onPrimaryContainer = Color.Yellow,
    inversePrimary = Color.Yellow,
    secondaryContainer = Color.Red,
    onSecondaryContainer = Color.Red,
    tertiaryContainer = Color.Yellow,
    onTertiaryContainer = Color.Red,
    onSurface = Color.Black,
    surfaceVariant = FieldBackgroundColor,
    onSurfaceVariant = TextLabelColor,
    surfaceTint = Color.Red,
    inverseSurface = Color.Red,
    inverseOnSurface = Color.Yellow,
    outline = Color.Blue,
    scrim = Color.Blue
)

private val LightColorScheme = lightColorScheme(
)

@Composable
fun OnlineShopSatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}