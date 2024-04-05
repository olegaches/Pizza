package com.olegaches.pizza.presentation.menu.content

import com.olegaches.pizza.model.Category

sealed interface MenuUiState {
    data object Loading: MenuUiState
    data class Success(val groupedMeal: List<Category>): MenuUiState
    data class Error(val groupedMeal: List<Category>?, val error: Throwable): MenuUiState
    data object None: MenuUiState
}