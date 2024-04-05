package com.olegaches.pizza.data.meal.di

import com.olegaches.pizza.ApplicationScope
import com.olegaches.pizza.data.meal.DefaultMealRepository
import com.olegaches.pizza.data.meal.MealRepository
import me.tatarka.inject.annotations.Provides

interface MealComponent {
    val DefaultMealRepository.bind: MealRepository
        @Provides @ApplicationScope get() = this
}