package com.murzify.foodies.core.domain.repository

import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun loadMeals()

    suspend fun loadCategories()

    suspend fun getMealsByCategory(category: Category): Flow<List<Meal>>

    suspend fun getCategories(): Flow<List<Category>>
}