package com.olegaches.pizza.database.meal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.olegaches.pizza.database.meal.dao.MealDao.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MealEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val ingredients: List<String>,
    val image: String,
    val category: String,
    val price: Double = 345.0,
)