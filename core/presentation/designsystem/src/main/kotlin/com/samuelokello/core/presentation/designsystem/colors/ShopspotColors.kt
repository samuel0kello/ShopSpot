package com.samuelokello.core.presentation.designsystem.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.samuelokello.core.presentation.designsystem.atoms.backgroundDark
import com.samuelokello.core.presentation.designsystem.atoms.backgroundLight
import com.samuelokello.core.presentation.designsystem.atoms.errorContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.errorContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.errorDark
import com.samuelokello.core.presentation.designsystem.atoms.errorLight
import com.samuelokello.core.presentation.designsystem.atoms.inverseOnSurfaceDark
import com.samuelokello.core.presentation.designsystem.atoms.inverseOnSurfaceLight
import com.samuelokello.core.presentation.designsystem.atoms.inversePrimaryDark
import com.samuelokello.core.presentation.designsystem.atoms.inversePrimaryLight
import com.samuelokello.core.presentation.designsystem.atoms.inverseSurfaceDark
import com.samuelokello.core.presentation.designsystem.atoms.inverseSurfaceLight
import com.samuelokello.core.presentation.designsystem.atoms.onBackgroundDark
import com.samuelokello.core.presentation.designsystem.atoms.onBackgroundLight
import com.samuelokello.core.presentation.designsystem.atoms.onErrorContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.onErrorContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.onErrorDark
import com.samuelokello.core.presentation.designsystem.atoms.onErrorLight
import com.samuelokello.core.presentation.designsystem.atoms.onPrimaryContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.onPrimaryContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.onPrimaryDark
import com.samuelokello.core.presentation.designsystem.atoms.onPrimaryLight
import com.samuelokello.core.presentation.designsystem.atoms.onSecondaryContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.onSecondaryContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.onSecondaryDark
import com.samuelokello.core.presentation.designsystem.atoms.onSecondaryLight
import com.samuelokello.core.presentation.designsystem.atoms.onSurfaceDark
import com.samuelokello.core.presentation.designsystem.atoms.onSurfaceLight
import com.samuelokello.core.presentation.designsystem.atoms.onSurfaceVariantDark
import com.samuelokello.core.presentation.designsystem.atoms.onSurfaceVariantLight
import com.samuelokello.core.presentation.designsystem.atoms.onTertiaryContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.onTertiaryContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.onTertiaryDark
import com.samuelokello.core.presentation.designsystem.atoms.onTertiaryLight
import com.samuelokello.core.presentation.designsystem.atoms.outlineDark
import com.samuelokello.core.presentation.designsystem.atoms.outlineLight
import com.samuelokello.core.presentation.designsystem.atoms.outlineVariantDark
import com.samuelokello.core.presentation.designsystem.atoms.outlineVariantLight
import com.samuelokello.core.presentation.designsystem.atoms.primaryContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.primaryContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.primaryDark
import com.samuelokello.core.presentation.designsystem.atoms.primaryLight
import com.samuelokello.core.presentation.designsystem.atoms.scrimDark
import com.samuelokello.core.presentation.designsystem.atoms.scrimLight
import com.samuelokello.core.presentation.designsystem.atoms.secondaryContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.secondaryContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.secondaryDark
import com.samuelokello.core.presentation.designsystem.atoms.secondaryLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceBrightDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceBrightLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerHighDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerHighLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerHighestDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerHighestLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerLowDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerLowLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerLowestDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceContainerLowestLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceDimDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceDimLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceLight
import com.samuelokello.core.presentation.designsystem.atoms.surfaceVariantDark
import com.samuelokello.core.presentation.designsystem.atoms.surfaceVariantLight
import com.samuelokello.core.presentation.designsystem.atoms.tertiaryContainerDark
import com.samuelokello.core.presentation.designsystem.atoms.tertiaryContainerLight
import com.samuelokello.core.presentation.designsystem.atoms.tertiaryDark
import com.samuelokello.core.presentation.designsystem.atoms.tertiaryLight

@Immutable
data class ShopspotColors(
    val primary: Color = primaryLight,
    val onPrimary: Color = onPrimaryLight,
    val primaryContainer: Color = primaryContainerLight,
    val onPrimaryContainer: Color = onPrimaryContainerLight,
    val secondary: Color = secondaryLight,
    val onSecondary: Color = onSecondaryLight,
    val secondaryContainer: Color = secondaryContainerLight,
    val onSecondaryContainer: Color = onSecondaryContainerLight,
    val tertiary: Color = tertiaryLight,
    val onTertiary: Color = onTertiaryLight,
    val tertiaryContainer: Color = tertiaryContainerLight,
    val onTertiaryContainer: Color = onTertiaryContainerLight,
    val error: Color = errorLight,
    val onError: Color = onErrorLight,
    val errorContainer: Color = errorContainerLight,
    val onErrorContainer: Color = onErrorContainerLight,
    val background: Color = backgroundLight,
    val onBackground: Color = onBackgroundLight,
    val surface: Color = surfaceLight,
    val onSurface: Color = onSurfaceLight,
    val surfaceVariant: Color = surfaceVariantLight,
    val onSurfaceVariant: Color = onSurfaceVariantLight,
    val outline: Color = outlineLight,
    val outlineVariant: Color = outlineVariantLight,
    val scrim: Color = scrimLight,
    val inverseSurface: Color = inverseSurfaceLight,
    val inverseOnSurface: Color = inverseOnSurfaceLight,
    val inversePrimary: Color = inversePrimaryLight,
    val surfaceDim: Color = surfaceDimLight,
    val surfaceBright: Color = surfaceBrightLight,
    val surfaceContainerLowest: Color = surfaceContainerLowestLight,
    val surfaceContainerLow: Color = surfaceContainerLowLight,
    val surfaceContainer: Color = surfaceContainerLight,
    val surfaceContainerHigh: Color = surfaceContainerHighLight,
    val surfaceContainerHighest: Color = surfaceContainerHighestLight,
)

val LocalShopspotColorsPalette = staticCompositionLocalOf { ShopspotColors() }

val ShopspotLightScheme =

    ShopspotColors(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        tertiaryContainer = tertiaryContainerLight,
        onTertiaryContainer = onTertiaryContainerLight,
        error = errorLight,
        onError = onErrorLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        background = backgroundLight,
        onBackground = onBackgroundLight,
        surface = surfaceLight,
        onSurface = onSurfaceLight,
        surfaceVariant = surfaceVariantLight,
        onSurfaceVariant = onSurfaceVariantLight,
        outline = outlineLight,
        outlineVariant = outlineVariantLight,
        scrim = scrimLight,
        inverseSurface = inverseSurfaceLight,
        inverseOnSurface = inverseOnSurfaceLight,
        inversePrimary = inversePrimaryLight,
        surfaceDim = surfaceDimLight,
        surfaceBright = surfaceBrightLight,
        surfaceContainerLowest = surfaceContainerLowestLight,
        surfaceContainerLow = surfaceContainerLowLight,
        surfaceContainer = surfaceContainerLight,
        surfaceContainerHigh = surfaceContainerHighLight,
        surfaceContainerHighest = surfaceContainerHighestLight,
    )

val ShopspotDarkScheme =
    ShopspotColors(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        tertiaryContainer = tertiaryContainerDark,
        onTertiaryContainer = onTertiaryContainerDark,
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        onSurface = onSurfaceDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
        outline = outlineDark,
        outlineVariant = outlineVariantDark,
        scrim = scrimDark,
        inverseSurface = inverseSurfaceDark,
        inverseOnSurface = inverseOnSurfaceDark,
        inversePrimary = inversePrimaryDark,
        surfaceDim = surfaceDimDark,
        surfaceBright = surfaceBrightDark,
        surfaceContainerLowest = surfaceContainerLowestDark,
        surfaceContainerLow = surfaceContainerLowDark,
        surfaceContainer = surfaceContainerDark,
        surfaceContainerHigh = surfaceContainerHighDark,
        surfaceContainerHighest = surfaceContainerHighestDark,
    )
