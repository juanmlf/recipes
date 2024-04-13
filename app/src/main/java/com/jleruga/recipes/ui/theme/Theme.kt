package com.jleruga.recipes.ui.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.TypedValue
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Green80,
    secondary = GreenGrey80,
    tertiary = LightGreen
)

private val LightColorScheme = lightColorScheme(
    primary = Color.White,
    secondary = DarkGreen,
    tertiary = DarkestGreen,
    onPrimary = LightGreen,
    onPrimaryContainer = LightGreen,
    onSecondaryContainer = LightGreen,
    onTertiaryContainer = LightGreen,
    primaryContainer = LightGreen,
    secondaryContainer = Color.DarkGray,
    tertiaryContainer = Color.DarkGray,
    onBackground = Color.White,


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
fun RecipesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    val rememberedColors = remember { colors.copy() }
    MaterialTheme(
        rememberedColors,
        //LocalSpaces provides spaces,
        typography = LocalTypography.current,
        content = content
    )

}

object RecipesTheme {
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val typography : Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

//val LocalSpaces = staticCompositionLocalOf { CustomSpaces() }

val LocalColors = staticCompositionLocalOf { LightColorScheme }

val LocalTypography = staticCompositionLocalOf { RecipeTypography }