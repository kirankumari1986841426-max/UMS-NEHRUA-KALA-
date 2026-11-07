package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = SchoolNavy,
    onPrimary = Color.White,
    primaryContainer = SchoolPale,
    onPrimaryContainer = SchoolNavyDark,
    secondary = SchoolBlue,
    onSecondary = Color.White,
    secondaryContainer = SchoolSurfaceVariant,
    onSecondaryContainer = SchoolInk,
    tertiary = SchoolGoldDark,
    onTertiary = Color.White,
    tertiaryContainer = SchoolGoldLight,
    onTertiaryContainer = Color(0xFF332000),
    background = SchoolPale,
    onBackground = SchoolInk,
    surface = SchoolSurface,
    onSurface = SchoolInk,
    surfaceVariant = SchoolSurfaceVariant,
    onSurfaceVariant = SchoolMuted,
    outline = Color(0xFFC7D3E3),
    outlineVariant = Color(0xFFE2EAF4)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = SchoolNavyDark,
    primaryContainer = SchoolNavy,
    onPrimaryContainer = Color(0xFFE1F0FF),
    secondary = Color(0xFF81D4FA),
    onSecondary = Color(0xFF00344D),
    secondaryContainer = Color(0xFF0D3E6B),
    onSecondaryContainer = Color(0xFFD4E6FA),
    tertiary = SchoolGold,
    onTertiary = Color(0xFF402D00),
    tertiaryContainer = Color(0xFF5C4300),
    onTertiaryContainer = SchoolGoldLight,
    background = Color(0xFF0C1929),
    onBackground = Color(0xFFECEFF4),
    surface = Color(0xFF132238),
    onSurface = Color(0xFFECEFF4),
    surfaceVariant = Color(0xFF1B2E4A),
    onSurfaceVariant = Color(0xFFB0C4DE),
    outline = Color(0xFF3D5370),
    outlineVariant = Color(0xFF23364F)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // We prefer the school's signature navy & gold palette by default for branding fidelity
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
