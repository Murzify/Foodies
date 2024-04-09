package com.murzify.gosporttest.core.domain.repository

import com.murzify.gosporttest.core.domain.model.Category
import com.murzify.gosporttest.core.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun loadMeals()

    suspend fun loadCategories()

    suspend fun getMealsByCategory(category: Category): Flow<List<Meal>>

    suspend fun getCategories(): Flow<List<Category>>
}