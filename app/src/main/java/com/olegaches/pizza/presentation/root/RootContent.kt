package com.olegaches.pizza.presentation.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.olegaches.pizza.presentation.menu.MenuScreen

@Composable
fun RootContent(component: RootComponent) {
    Scaffold(
        bottomBar = {
            val navigationBarsHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            NavigationBar(
                rootComponent = component,
                modifier = Modifier
                    .shadow(elevation = 11.dp)
                    .height(56.dp + navigationBarsHeight)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(bottom = navigationBarsHeight)
            )
        }
    ) { paddingValues ->
        Children(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
                .fillMaxSize(),
            stack = component.childStack,
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Menu -> {
                    MenuScreen(child.component)
                }
                is RootComponent.Child.Cart -> TODO()
                is RootComponent.Child.Profile -> TODO()
            }
        }
    }
}