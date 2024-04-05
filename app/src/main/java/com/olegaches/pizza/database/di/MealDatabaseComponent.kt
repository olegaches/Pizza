package com.olegaches.pizza.database.di

import android.app.Application
import androidx.room.Room
import com.olegaches.pizza.ApplicationScope
import com.olegaches.pizza.database.meal.MealDatabase
import com.olegaches.pizza.database.meal.dao.MealDao
import me.tatarka.inject.annotations.Provides

interface MealDatabaseComponent {
    @Provides
    @ApplicationScope
    fun provideDao(application: Application): MealDao {
        return Room.databaseBuilder(
            context = application.applicationContext,
            MealDatabase::class.java,
            MealDatabase.NAME,
        )
            .build()
            .mealDao
    }
}