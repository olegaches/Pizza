package com.olegaches.pizza.presentation.menu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.olegaches.pizza.presentation.menu.content.MenuContent
import com.olegaches.pizza.presentation.menu.topbar.MenuTopBar

@Composable
fun MenuScreen(menu: MenuComponent) {
    Scaffold(
        topBar = {
            MenuTopBar(menu.menuTopBarComponent)
        }
    ) { paddingValues ->
        MenuContent(menu.menuContentComponent, Modifier.padding(top = paddingValues.calculateTopPadding()).fillMaxSize())
    }
}