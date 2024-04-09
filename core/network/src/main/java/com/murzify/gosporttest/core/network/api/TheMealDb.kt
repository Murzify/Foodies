package com.murzify.gosporttest.core.network.api

import com.murzify.gosporttest.core.network.model.CategoriesDto
import com.murzify.gosporttest.core.network.model.MealsDto

interface TheMealDb {
    suspend fun search(): MealsDto

    suspend fun categories(): CategoriesDto
}