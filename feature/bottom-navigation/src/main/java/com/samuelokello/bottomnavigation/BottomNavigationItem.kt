package com.samuelokello.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.samuelokello.core.util.Screens

sealed class BottomNavItem(
    val label: String = "",
    val icon: ImageVector = Icons.Default.Home,
    val route: String = "",
) {
    data object Home : BottomNavItem("Home", Icons.Default.Home, Screens.Home.route)

    data object Search : BottomNavItem("Search", Icons.Filled.Search, Screens.Search.route)

//            object BottomNavItem("Favourite",Icons.Rounded.Favorite, Screens.Favourite.route),

    data object Profile : BottomNavItem("Profile", Icons.Rounded.Person, Screens.Profile.route)

    companion object {
        val items = listOf(Home, Search, Profile)
    }
}
