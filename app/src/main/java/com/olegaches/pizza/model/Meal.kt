package com.olegaches.pizza.model

data class Meal(
    val id: Long,
    val name: String,
    val ingredients: List<String>,
    val image: String,
    val category: String,
    val price: Double = 345.0,
)
