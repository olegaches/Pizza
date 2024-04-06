package com.olegaches.pizza.data.meal.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealResponseDto(
    @Json(name = "meals")
    val mealList: List<MealDto>
)
