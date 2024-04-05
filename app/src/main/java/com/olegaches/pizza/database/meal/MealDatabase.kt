package com.olegaches.pizza.database.meal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.olegaches.pizza.database.meal.converter.StringListConverter
import com.olegaches.pizza.database.meal.dao.MealDao
import com.olegaches.pizza.database.meal.entity.MealEntity

@Database(
    entities = [MealEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(StringListConverter::class)
abstract class MealDatabase: RoomDatabase() {
    abstract val mealDao: MealDao

    companion object {
        const val NAME = "meal.db"
    }
}