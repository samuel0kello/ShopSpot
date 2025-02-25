package com.samuelokello.bottomnavigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samuelokello.home.HomeScreen
import com.samuelokello.profile.ProfileScreen
import com.samuelokello.search.SearchScreen

@Composable
fun BottomNavigationScreen(
    startTab: BottomNavItem = BottomNavItem.Home,
    onTabSelected: (BottomNavItem) -> Unit,
    viewModel: BottomNavigationViewModel = viewModel(),
) {
    val activity = LocalActivity.current as Activity
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Handle back press
    BackHandler {
        if (state.currentTab != BottomNavItem.Home) {
            viewModel.onTabSelected(BottomNavItem.Home)
        } else {
            activity.finish()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = state.currentTab,
                onTabSelected = viewModel::onTabSelected,
            )
        },
    ) { padding ->
        AnimatedContent(
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
            targetState = state.currentTab,
            label = "animated_tabs",
        ) { tab ->
            when (tab) {
                BottomNavItem.Home ->
                    HomeScreen(
                        navigateToItemDetails = {},
                        onBackPressed = {},
                    )
                BottomNavItem.Search ->
                    SearchScreen(
                        navigateToItemDetails = {},
                    )
                BottomNavItem.Profile -> ProfileScreen()
            }
        }
    }
}
