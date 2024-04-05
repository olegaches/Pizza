package com.olegaches.pizza

import android.app.Application
import android.content.Context
import com.olegaches.pizza.meal_api.di.MealApiComponent
import com.olegaches.pizza.data.meal.di.MealComponent
import com.olegaches.pizza.database.di.MealDatabaseComponent
import com.olegaches.shared.DispatcherProviderComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

/**
 * The application-level scope. There will only be one instance of anything annotated with this.
 */
@Scope
annotation class ApplicationScope

/**
 * The main application component. Use [getInstance] to ensure the same instance is shared.
 */
@Component
@ApplicationScope
abstract class ApplicationComponent(
    @get:Provides val application: Application,
): MealComponent, MealApiComponent, DispatcherProviderComponent, MealDatabaseComponent {
    companion object {
        private var instance: ApplicationComponent? = null

        /**
         * Get a singleton instance of [ApplicationComponent].
         */
        fun getInstance(context: Context) = instance ?: ApplicationComponent::class.create(
            context.applicationContext as Application
        ).also { instance = it }
    }
}