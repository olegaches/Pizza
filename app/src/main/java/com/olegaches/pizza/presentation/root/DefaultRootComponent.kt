package com.olegaches.pizza.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import com.olegaches.pizza.presentation.menu.DefaultMenuComponent
import kotlinx.serialization.Serializable
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class DefaultRootComponent(
    @Assisted
    componentContext: ComponentContext,
    private val menuComponent: (ComponentContext) -> DefaultMenuComponent
): RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Menu,
            handleBackButton = true,
            childFactory = ::child,
        )

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onMenuClicked() {
        navigation.pushToFront(Config.Menu)
    }

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCartClicked() {
        navigation.pushToFront(Config.Menu)
    }

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onProfileClicked() {
        navigation.pushToFront(Config.Menu)
    }

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when(config) {
            is Config.Menu -> {
                RootComponent.Child.Menu(menuComponent(componentContext))
            }
            Config.Cart -> TODO()
            Config.Profile -> TODO()
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Menu : Config
        @Serializable
        data object Cart : Config
        @Serializable
        data object Profile : Config
    }
}