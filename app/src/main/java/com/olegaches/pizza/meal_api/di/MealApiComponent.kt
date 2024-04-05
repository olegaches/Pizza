package com.olegaches.pizza.meal_api.di

import com.olegaches.pizza.ApplicationScope
import com.olegaches.pizza.meal_api.MealApi
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.tatarka.inject.annotations.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

interface MealApiComponent {
    @Provides
    @ApplicationScope
    fun provideMealApi(): MealApi {
        return Retrofit
            .Builder()
            .baseUrl(MealApi.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                ))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
            .create()
    }
}