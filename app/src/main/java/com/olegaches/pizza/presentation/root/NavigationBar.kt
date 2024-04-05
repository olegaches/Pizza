package com.olegaches.pizza.presentation.root

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.olegaches.pizza.R

@Composable
fun NavigationBar(rootComponent: RootComponent, modifier: Modifier) {
    val childStack by rootComponent.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance
    Row(
        modifier = modifier
    ) {
        NavBarItem(
            selected = activeComponent is RootComponent.Child.Menu,
            onClick = rootComponent::onMenuClicked,
            icon = Icons.Default.Home,
            label = stringResource(R.string.menu)
        )
        NavBarItem(
            selected = activeComponent is RootComponent.Child.Profile,
            onClick = rootComponent::onProfileClicked,
            icon = Icons.Default.Person,
            label = stringResource(R.string.profile)
        )
        NavBarItem(
            selected = activeComponent is RootComponent.Child.Cart,
            onClick = rootComponent::onCartClicked,
            icon = Icons.Default.ShoppingCart,
            label = stringResource(R.string.cart)
        )
    }
}

@Composable
fun RowScope.NavBarItem(
    selected: Boolean,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val color = if (selected) Color.Red else Color.Gray
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color
        )
        Text(
            text = label,
            color = color,
            fontSize = 12.sp
        )
    }
}