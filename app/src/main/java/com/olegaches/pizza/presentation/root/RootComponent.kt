package com.olegaches.pizza.presentation.root

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.olegaches.pizza.presentation.menu.MenuComponent

@Stable
interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Menu(val component: MenuComponent) : Child
        data class Cart(val component: MenuComponent) : Child
        data class Profile(val component: MenuComponent) : Child
    }

    fun onMenuClicked()
    fun onCartClicked()
    fun onProfileClicked()
}