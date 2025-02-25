package com.samuelokello.bottomnavigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class BottomNavigationState(
    val currentTab: BottomNavItem = BottomNavItem.Home,
)

class BottomNavigationViewModel : ViewModel() {
    private val _state = MutableStateFlow(BottomNavigationState())
    val state: StateFlow<BottomNavigationState> = _state

    fun onTabSelected(tab: BottomNavItem) {
        _state.update {
            it.copy(currentTab = tab)
        }
    }
}
