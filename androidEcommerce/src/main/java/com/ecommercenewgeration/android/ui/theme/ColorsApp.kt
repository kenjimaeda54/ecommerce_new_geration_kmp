package com.ecommercenewgeration.android.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


val lightColors = lightColorScheme(

    primary = Color(0xFF000000),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFF808080),
    onSecondary = Color(0xFFFFFFFF),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),

    surface = Color(0xFFF7F7F7),
    onSurface = Color(0xFF000000),

    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF808080),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF)
)

val darkColors = darkColorScheme(

    primary = Color(0xFFE0E0E0),
    onPrimary = Color(0xFF121212),

    secondary = Color(0xFFA0A0A0),
    onSecondary = Color(0xFF1E1E1E),

    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),

    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),

    surfaceVariant = Color(0xFF242424),
    onSurfaceVariant = Color(0xFFA0A0A0),

    error = Color(0xFFCF6679),
    onError = Color(0xFF000000)
)