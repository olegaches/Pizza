package com.olegaches.pizza.presentation.menu

import com.arkivanov.decompose.ComponentContext
import com.olegaches.pizza.presentation.menu.content.DefaultMenuContentComponent
import com.olegaches.pizza.presentation.menu.topbar.DefaultMenuTopBarComponent
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class DefaultMenuComponent(
    @Assisted
    componentContext: ComponentContext,
    topBarComponent: (ComponentContext) -> DefaultMenuTopBarComponent,
    menuContentComponent: (ComponentContext) -> DefaultMenuContentComponent
): MenuComponent, ComponentContext by componentContext {
    override val menuTopBarComponent = topBarComponent(componentContext)
    override val menuContentComponent = menuContentComponent(componentContext)
}