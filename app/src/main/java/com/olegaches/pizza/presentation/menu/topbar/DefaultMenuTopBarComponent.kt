package com.olegaches.pizza.presentation.menu.topbar

import com.arkivanov.decompose.ComponentContext
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class DefaultMenuTopBarComponent(
    @Assisted
    componentContext: ComponentContext
): MenuTopBarComponent, ComponentContext by componentContext {
}