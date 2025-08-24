package com.samuelokello.core.presentation.designsystem.components.bottomnav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ShopSpotBottomNavigation(
    selectedIndex: Int,
    onNavigate: (Int, String) -> Unit,
) {
    NavigationBar(containerColor = Color.Transparent) {
        BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = {
                    onNavigate(index, item.route)
                },
                icon = { Icon(item.icon, contentDescription = null) },
                modifier = Modifier,
                label = {
                    Text(text = item.label)
                },
            )
        }
    }
}
