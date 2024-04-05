package com.olegaches.pizza.meal_api

import com.olegaches.pizza.data.meal.dto.MealResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun search(
        @Query("s")
        query: String? = ""
    ): Result<MealResponseDto>

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}