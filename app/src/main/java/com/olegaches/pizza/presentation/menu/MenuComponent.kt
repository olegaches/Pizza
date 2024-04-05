package com.olegaches.pizza.presentation.menu

import androidx.compose.runtime.Stable
import com.olegaches.pizza.presentation.menu.content.MenuContentComponent
import com.olegaches.pizza.presentation.menu.topbar.MenuTopBarComponent

@Stable
interface MenuComponent {
    val menuTopBarComponent: MenuTopBarComponent
    val menuContentComponent: MenuContentComponent
}