package com.olegaches.pizza.database.meal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.olegaches.pizza.database.meal.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMeal(meal: MealEntity)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMealList(meal: List<MealEntity>)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id")
    fun getAll(): Flow<List<MealEntity>>

    @Transaction
    suspend fun deleteAndInsertAll(items: List<MealEntity>) {
        clear()
        saveMealList(items)
    }

    companion object {
        const val TABLE_NAME = "meal"
    }
}