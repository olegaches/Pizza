package com.olegaches.pizza.data.meal.dto

import com.squareup.moshi.Json

data class MealResponseDto(
    @Json(name = "meals")
    val mealList: List<MealDto>
)
