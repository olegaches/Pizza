package com.olegaches.pizza

import com.arkivanov.decompose.ComponentContext
import com.olegaches.pizza.presentation.root.DefaultRootComponent
import com.olegaches.pizza.presentation.root.RootComponent
import me.tatarka.inject.annotations.Inject

@Inject
class InjectRootFactory(
    private val rootComponent: (ComponentContext) -> DefaultRootComponent,
) {
    fun createRoot(componentContext: ComponentContext): RootComponent {
        return rootComponent(componentContext)
    }
}