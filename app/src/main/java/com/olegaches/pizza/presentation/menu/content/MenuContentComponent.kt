package com.olegaches.pizza.presentation.menu.content

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MenuContentComponent {
    val state: StateFlow<MenuUiState>
    val events: Flow<MenuContentEvents>
}