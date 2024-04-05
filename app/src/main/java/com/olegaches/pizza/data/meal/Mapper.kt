package com.olegaches.pizza.data.meal

import com.olegaches.pizza.data.meal.dto.MealDto
import com.olegaches.pizza.database.meal.entity.MealEntity
import com.olegaches.pizza.model.Meal

fun MealDto.toMeal(): Meal {
    return Meal(
        id = id,
        name = name,
        image = image,
        category = category,
        ingredients = mutableListOf<String>().apply {
            if(!ingredient1.isNullOrEmpty()) add(ingredient1) else return@apply
            if(!ingredient2.isNullOrEmpty()) add(ingredient2) else return@apply
            if(!ingredient3.isNullOrEmpty()) add(ingredient3) else return@apply
            if(!ingredient4.isNullOrEmpty()) add(ingredient4) else return@apply
            if(!ingredient5.isNullOrEmpty()) add(ingredient5) else return@apply
            if(!ingredient6.isNullOrEmpty()) add(ingredient6) else return@apply
            if(!ingredient7.isNullOrEmpty()) add(ingredient7) else return@apply
            if(!ingredient8.isNullOrEmpty()) add(ingredient8) else return@apply
            if(!ingredient9.isNullOrEmpty()) add(ingredient9) else return@apply
            if(!ingredient10.isNullOrEmpty()) add(ingredient10) else return@apply
            if(!ingredient11.isNullOrEmpty()) add(ingredient11) else return@apply
            if(!ingredient12.isNullOrEmpty()) add(ingredient12) else return@apply
            if(!ingredient13.isNullOrEmpty()) add(ingredient13) else return@apply
            if(!ingredient14.isNullOrEmpty()) add(ingredient14) else return@apply
            if(!ingredient15.isNullOrEmpty()) add(ingredient15) else return@apply
            if(!ingredient16.isNullOrEmpty()) add(ingredient16) else return@apply
            if(!ingredient17.isNullOrEmpty()) add(ingredient17) else return@apply
            if(!ingredient18.isNullOrEmpty()) add(ingredient18) else return@apply
            if(!ingredient19.isNullOrEmpty()) add(ingredient19) else return@apply
            if(!ingredient20.isNullOrEmpty()) add(ingredient20) else return@apply
        },
    )
}

fun MealDto.toMealEntity(): MealEntity {
    return MealEntity(
        id = id,
        name = name,
        image = image,
        category = category,
        ingredients = mutableListOf<String>().apply {
            if(!ingredient1.isNullOrEmpty()) add(ingredient1) else return@apply
            if(!ingredient2.isNullOrEmpty()) add(ingredient2) else return@apply
            if(!ingredient3.isNullOrEmpty()) add(ingredient3) else return@apply
            if(!ingredient4.isNullOrEmpty()) add(ingredient4) else return@apply
            if(!ingredient5.isNullOrEmpty()) add(ingredient5) else return@apply
            if(!ingredient6.isNullOrEmpty()) add(ingredient6) else return@apply
            if(!ingredient7.isNullOrEmpty()) add(ingredient7) else return@apply
            if(!ingredient8.isNullOrEmpty()) add(ingredient8) else return@apply
            if(!ingredient9.isNullOrEmpty()) add(ingredient9) else return@apply
            if(!ingredient10.isNullOrEmpty()) add(ingredient10) else return@apply
            if(!ingredient11.isNullOrEmpty()) add(ingredient11) else return@apply
            if(!ingredient12.isNullOrEmpty()) add(ingredient12) else return@apply
            if(!ingredient13.isNullOrEmpty()) add(ingredient13) else return@apply
            if(!ingredient14.isNullOrEmpty()) add(ingredient14) else return@apply
            if(!ingredient15.isNullOrEmpty()) add(ingredient15) else return@apply
            if(!ingredient16.isNullOrEmpty()) add(ingredient16) else return@apply
            if(!ingredient17.isNullOrEmpty()) add(ingredient17) else return@apply
            if(!ingredient18.isNullOrEmpty()) add(ingredient18) else return@apply
            if(!ingredient19.isNullOrEmpty()) add(ingredient19) else return@apply
            if(!ingredient20.isNullOrEmpty()) add(ingredient20) else return@apply
        },
    )
}

fun MealEntity.toMeal(): Meal {
    return Meal(
        id = id,
        name = name,
        image = image,
        category = category,
        ingredients = ingredients
    )
}