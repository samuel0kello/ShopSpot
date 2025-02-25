package com.samuelokello.commonui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.samuelokello.core.util.Screens
import com.samuelokello.shopspot.R

@Composable
fun topBarManager(
    currentRoute: String?,
    navigateToCart: () -> Unit,
): TopBarConfig =
    when (currentRoute) {
        Screens.Register.route ->
            TopBarConfig(
                title = "",
                topBarType = TopBarType.CenterAligned,
                showBackIcon = true,
                actions = { },
            )
        Screens.Login.route ->
            TopBarConfig(
                title = "",
                topBarType = TopBarType.CenterAligned,
                showBackIcon = true,
                actions = {},
            )
        Screens.ForgotPassword.route ->
            TopBarConfig(
                title = stringResource(R.string.forgot_password),
                topBarType = TopBarType.CenterAligned,
                showBackIcon = true,
                actions = {
                },
            )
        Screens.Home.route ->
            TopBarConfig(
                title = stringResource(R.string.shopez),
                topBarType = TopBarType.CenterAligned,
                actions = {
                    IconButton(onClick = navigateToCart) {
                        Icon(Icons.Default.AddShoppingCart, contentDescription = stringResource(R.string.cart))
                    }
                },
            )

        Screens.Cart.route ->
            TopBarConfig(
                title = stringResource(R.string.my_cart),
                topBarType = TopBarType.CenterAligned,
                showBackIcon = true,
            )

        Screens.Profile.route ->
            TopBarConfig(
                title = "",
                topBarType = TopBarType.Regular,
            )

        "${Screens.ProductDetailsScreen.route}/{productId}" ->
            TopBarConfig(
                title = "",
                topBarType = TopBarType.CenterAligned,
                actions = {
                    IconButton(
                        onClick = navigateToCart,
                    ) {
                        Icon(Icons.Default.AddShoppingCart, contentDescription = stringResource(R.string.add_to_favourite))
                    }
                },
                showBackIcon = true,
            )
//        Screens.Search.route -> TopBarConfig(
//            title = "",
//            topBarType = TopBarType.CenterAligned,
//            showBackIcon = false,
//        )
        Screens.Favourite.route ->
            TopBarConfig(
                title = "",
                topBarType = TopBarType.CenterAligned,
                showBackIcon = false,
            )

        else ->
            TopBarConfig(
                title = "",
                topBarType = TopBarType.Regular,
            )
    }
