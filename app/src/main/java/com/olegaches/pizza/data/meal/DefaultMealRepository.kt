package com.olegaches.pizza.data.meal

import com.olegaches.pizza.database.meal.dao.MealDao
import com.olegaches.pizza.database.meal.entity.MealEntity
import com.olegaches.pizza.meal_api.MealApi
import com.olegaches.pizza.model.Meal
import com.olegaches.shared.IoDispatcher
import com.olegaches.shared.util.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import me.tatarka.inject.annotations.Inject

@Inject
class DefaultMealRepository(
    private val api: MealApi,
    private val ioDispatcher: IoDispatcher,
    private val mealDao: MealDao,
): MealRepository {
    override fun getAllMeal(): Flow<RequestResult<List<Meal>>> {
        val zippedFlow = getApiMealList().combine(getDbMealList()) { apiResult, dbResult ->
            when(apiResult) {
                is RequestResult.Loading -> {
                    RequestResult.Loading()
                }
                is RequestResult.Error -> {
                    RequestResult.Error(data = (dbResult as RequestResult.Success).data, error = apiResult.error)
                }
                is RequestResult.Success -> {
                    apiResult
                }
            }
        }
        return zippedFlow.distinctUntilChanged().flowOn(ioDispatcher)
    }

    private fun getApiMealList() = flow<RequestResult<List<Meal>>> {
        emit(RequestResult.Loading())
        val result = api.search()
        result.onSuccess { mealResponse ->
            val entityList = mealResponse.mealList.asSequence().sortedBy { it.id }.map { it.toMealEntity() }.toList()
            mealDao.deleteAndInsertAll(entityList)
            emit(RequestResult.Success(entityList.map { it.toMeal() }))
        }.onFailure {
            emit(RequestResult.Error(error = it))
        }
    }.flowOn(ioDispatcher)

    private fun getDbMealList() = mealDao.getAll()
        .map<List<MealEntity>, RequestResult<List<Meal>>> { entityList ->
            RequestResult.Success(entityList.map { it.toMeal() })
        }
        .onStart {
            emit(RequestResult.Loading())
        }
        .flowOn(ioDispatcher)
}