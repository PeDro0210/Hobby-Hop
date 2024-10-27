package com.pedro0210.hobbylobby.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

//TODO: Set the schemes (BRANDON)
private val DarkColorScheme = darkColorScheme(
    primary = DPink1,
    secondary = DBlue1,
    tertiary = DGreen1,
    onPrimary = DPink2,
    onSecondary = DBlue3,
    onTertiary = DGreen2,

    surface = DBlue2,
    onSurface = DPink3,

    outline = DPink4,

    background = Gray1,
    surfaceBright = Gray2,
)

private val LightColorScheme = lightColorScheme(
    primary = Pink1,
    secondary = Blue1,
    tertiary = Green1,
    onPrimary = Pink2,
    onSecondary = Blue3,
    onTertiary = Green2,

    surface = Blue2,
    onSurface = Pink3,

    outline = Pink4,

    background = White1,
    surfaceBright = White2,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun HobbyLobbyTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}