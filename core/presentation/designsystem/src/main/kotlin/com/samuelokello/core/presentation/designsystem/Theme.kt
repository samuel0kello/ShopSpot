package com.samuelokello.core.presentation.designsystem

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.samuelokello.core.presentation.designsystem.colors.LocalShopspotColorsPalette
import com.samuelokello.core.presentation.designsystem.colors.ShopspotColors
import com.samuelokello.core.presentation.designsystem.colors.ShopspotDarkScheme
import com.samuelokello.core.presentation.designsystem.colors.ShopspotLightScheme

@Composable
fun ShopSpotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    val customColorScheme = if (darkTheme) ShopspotDarkScheme else ShopspotLightScheme

    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context.findActivity()
            activity.window.statusBarColor = customColorScheme.background.toArgb()
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalShopspotColorsPalette provides customColorScheme,
    ) {
        MaterialTheme(content = content)
    }
}

val MaterialTheme.shopSpotColorScheme: ShopspotColors
    @Composable
    @ReadOnlyComposable
    get() = LocalShopspotColorsPalette.current

private fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Activity absent")
}
