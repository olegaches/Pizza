package com.olegaches.pizza.model

data class Category(
    val categoryName: String,
    val mealList: List<Meal>
)
