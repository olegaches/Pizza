package com.olegaches.pizza.data.meal

import com.olegaches.pizza.model.Meal
import com.olegaches.shared.util.RequestResult
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getAllMeal(): Flow<RequestResult<List<Meal>>>
}