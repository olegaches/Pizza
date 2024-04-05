package com.olegaches.pizza.presentation.menu.content

import com.arkivanov.decompose.ComponentContext
import com.olegaches.pizza.domain.meal.GetMealGroupedByCategoryUseCase
import com.olegaches.pizza.presentation.util.coroutineScope
import com.olegaches.shared.MainImmediateDispatcher
import com.olegaches.shared.util.RequestResult
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class DefaultMenuContentComponent(
    @Assisted
    componentContext: ComponentContext,
    mainImmediateDispatcher: MainImmediateDispatcher,
    getMealGroupedByCategoryUseCase: Lazy<GetMealGroupedByCategoryUseCase>,

): MenuContentComponent, ComponentContext by componentContext {

    private val componentScope = coroutineScope(mainImmediateDispatcher + SupervisorJob())

    override val state = getMealGroupedByCategoryUseCase.value()
        .map { requestResult ->
            when(requestResult) {
                is RequestResult.Error -> {
                    _events.send(MenuContentEvents.OnError(requestResult.error))
                    MenuUiState.Error(error = requestResult.error, groupedMeal = requestResult.data)
                }
                is RequestResult.Loading -> MenuUiState.Loading
                is RequestResult.Success -> MenuUiState.Success(requestResult.data)
            }
        }
        .stateIn(componentScope, SharingStarted.Lazily, MenuUiState.None)

    private val _events = Channel<MenuContentEvents>()
    override val events = _events.receiveAsFlow()
}
