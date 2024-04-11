package com.murzify.foodies.core.network.api

import com.murzify.foodies.core.network.model.CategoriesDto
import com.murzify.foodies.core.network.model.MealsDto

interface TheMealDb {
    suspend fun search(): MealsDto

    suspend fun categories(): CategoriesDto
}