package com.samuelokello.shopspot.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samuelokello.auth.auth_dashboard.AuthDashboardScreen
import com.samuelokello.auth.forgot_password.ForgotPasswordScreen
import com.samuelokello.auth.login.LoginScreen
import com.samuelokello.auth.register.RegisterScreen
import com.samuelokello.cart.CartScreen
import com.samuelokello.commonui.components.ShopSpotTopAppBar
import com.samuelokello.commonui.components.topBarManager
import com.samuelokello.core.util.Screens
import com.samuelokello.home.HomeScreen
import com.samuelokello.order.OrderPlacedScreen
import com.samuelokello.search.SearchScreen
import com.samuelokello.wishlist.FavouriteScreen

@Suppress("ktlint:standard:function-naming")
@Composable
fun ShopSpotAppNavHost(navigationViewModel: NavigationViewModel = viewModel()) {
//    val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    val topBarConfig =
        topBarManager(
            currentRoute.toString(),
            navigateToCart = { navController.navigate(Screens.Cart.route) },
        )

    var bottomBarVisible by remember { mutableStateOf(true) }
    val bottomBarHeight = 80.dp
    with(LocalDensity.current) { bottomBarHeight.toPx() }

    val nestedScrollConnection =
        remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource,
                ): Offset {
                    // Hide bottom bar on scroll down, show on scroll up
                    if (available.y < -10) {
                        bottomBarVisible = false
                    } else if (available.y > 10) {
                        bottomBarVisible = true
                    }
                    return Offset.Zero
                }
            }
        }

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        topBar = {
            ShopSpotTopAppBar(
                config = topBarConfig,
                onBackClick = { navController.popBackStack() },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.AuthDashBoard.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screens.AuthDashBoard.route) {
                AuthDashboardScreen(
                    navigateToLogin = { navController.navigate(Screens.Login.route) },
                    navigateToRegister = { navController.navigate(Screens.Register.route) },
                )
            }
            composable(Screens.Register.route) {
                RegisterScreen(
                    navigateToLogin = { navController.navigate(Screens.Login.route) },
                )
            }
            composable(Screens.Login.route) {
                LoginScreen(
                    navigateToRegister = { navController.navigate(Screens.Register.route) },
                    navigateToHome = { navController.navigate(Screens.Home.route) },
                    navigateToForgotPassword = { navController.navigate(Screens.ForgotPassword.route) },
                )
            }
            composable(Screens.ForgotPassword.route) {
                ForgotPasswordScreen(modifier = Modifier)
            }
            composable(Screens.Home.route) {
                HomeScreen(
                    navigateToItemDetails = { productId ->
                        navController.navigate("${Screens.ProductDetailsScreen.route}/$productId")
                    },
                    onBackPressed = {
//                        navigationViewModel.onBackPressed(context = context, activity = activity)
                    },
                )
            }
            composable(Screens.Cart.route) {
                CartScreen(
                    navigateToCheckout = { navController.navigate(Screens.OrderPlaced.route) },
                    navigateToHome = { navController.navigate(Screens.Home.route) },
                )
            }
            composable(Screens.Search.route) {
                SearchScreen(
                    modifier = Modifier,
                    navigateToItemDetails = { product ->
                        navController.navigate("${Screens.ProductDetailsScreen.route}/${product.id}")
                    },
                )
            }
            composable(Screens.Favourite.route) {
                FavouriteScreen(modifier = Modifier)
            }
            composable(Screens.Profile.route) {
                com.samuelokello.profile.ProfileScreen(modifier = Modifier)
            }
            composable(Screens.OrderPlaced.route) {
                OrderPlacedScreen(
                    navController = navController,
                )
            }
            composable(
                route = "${Screens.ProductDetailsScreen.route}/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType }),
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId")
                if (productId != null) {
                    com.samuelokello.products.productdetails
                        .ProductDetailsScreen(productId = productId)
                }
            }
        }
    }
}
