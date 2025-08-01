package com.samuelokello.shopspot.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samuelokello.shopspot.ui.AppViewModelProvider
import com.samuelokello.shopspot.ui.auth.authdashboard.AuthDashboardScreen
import com.samuelokello.shopspot.ui.auth.forgotpassword.ForgotPasswordScreen
import com.samuelokello.shopspot.ui.auth.login.LoginScreen
import com.samuelokello.shopspot.ui.auth.register.RegisterScreen
import com.samuelokello.shopspot.ui.cart.CartScreen
import com.samuelokello.shopspot.ui.components.ShopSpotTopAppBar
import com.samuelokello.shopspot.ui.components.topBarManager
import com.samuelokello.shopspot.ui.favourite.FavouriteScreen
import com.samuelokello.shopspot.ui.home.HomeScreen
import com.samuelokello.shopspot.ui.home.HomeViewModel
import com.samuelokello.shopspot.ui.navigation.bottomnavigation.SwipeAbleBottomNav
import com.samuelokello.shopspot.ui.order.OrderPlacedScreen
import com.samuelokello.shopspot.ui.productdetails.ProductDetailsScreen
import com.samuelokello.shopspot.ui.profile.ProfileScreen
import com.samuelokello.shopspot.ui.search.SearchScreen

@Composable
fun ShopSpotAppNavHost(navigationViewModel: NavigationViewModel = viewModel()) {
    val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
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

    val activity = LocalContext.current as ComponentActivity
    val context = LocalContext.current

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

    var selectedNavIndex by rememberSaveable {
        mutableIntStateOf(
            when (currentRoute) {
                Screens.Home.route -> 0
                Screens.Search.route -> 1
                Screens.Favourite.route -> 2
                Screens.Profile.route -> 3
                else -> 0
            },
        )
    }

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        topBar = {
            ShopSpotTopAppBar(
                config = topBarConfig,
                onBackClick = { navController.popBackStack() },
            )
        },
        bottomBar = {
            if (currentRoute in
                setOf(
                    Screens.Home.route,
                    Screens.Profile.route,
                    Screens.Favourite.route,
                    Screens.Search.route,
                )
            ) {
                SwipeAbleBottomNav(
                    selectedIndex = selectedNavIndex,
                    onNavigate = { index, route ->
                        selectedNavIndex = index
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    isVisible = bottomBarVisible,
                )
            }
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
                        navigationViewModel.onBackPressed(context = context, activity = activity)
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
                ProfileScreen(modifier = Modifier)
            }
            composable(Screens.OrderPlaced.route) {
                OrderPlacedScreen(navController = navController, viewModel = viewModel)
            }
            composable(
                route = "${Screens.ProductDetailsScreen.route}/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType }),
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId")
                if (productId != null) {
                    ProductDetailsScreen(productId = productId)
                }
            }
        }
    }
}
