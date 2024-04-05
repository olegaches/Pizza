package com.olegaches.pizza.presentation.menu.content

sealed interface MenuContentEvents {
    data class OnError(val error: Throwable): MenuContentEvents
}