package com.olegaches.shared

import com.olegaches.pizza.ApplicationScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import me.tatarka.inject.annotations.Provides

typealias IoDispatcher = CoroutineDispatcher
typealias MainDispatcher = CoroutineDispatcher
typealias MainImmediateDispatcher = CoroutineDispatcher
typealias DefaultDispatcher = CoroutineDispatcher
typealias AppCoroutineScope = CoroutineScope


interface DispatcherProviderComponent {
    @ApplicationScope
    @Provides
    fun provideDefaultDispatcher(): DefaultDispatcher = Dispatchers.Default

    @ApplicationScope
    @Provides
    fun provideIoDispatcher(): IoDispatcher = Dispatchers.IO

    @ApplicationScope
    @Provides
    fun provideMainDispatcher(): MainDispatcher = Dispatchers.Main

    @ApplicationScope
    @Provides
    fun provideMainImmediateDispatcher(): MainImmediateDispatcher = Dispatchers.Main.immediate

    @ApplicationScope
    @Provides
    fun provideApplicationCoroutineScope(defaultDispatcher: DefaultDispatcher): AppCoroutineScope {
        return CoroutineScope(SupervisorJob() + defaultDispatcher)
    }
}