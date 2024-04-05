package com.olegaches.pizza.domain.meal

import com.olegaches.pizza.data.meal.MealRepository
import com.olegaches.pizza.model.Category
import com.olegaches.shared.util.RequestResult
import com.olegaches.shared.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@Inject
class GetMealGroupedByCategoryUseCase(
    private val mealRepository: MealRepository
) {
    operator fun invoke(): Flow<RequestResult<List<Category>>> {
        return mealRepository.getAllMeal().map { requestResult ->
            requestResult.map { mealList ->
                mealList
                    .groupBy { meal -> meal.category }
                    .map { Category(it.key, it.value) }
            }
        }
    }
}