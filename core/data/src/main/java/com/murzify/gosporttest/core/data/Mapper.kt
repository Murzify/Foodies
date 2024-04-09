package com.murzify.gosporttest.core.data

import com.murzify.gosporttest.core.database.model.CategoryEntity
import com.murzify.gosporttest.core.database.model.MealAndCategory
import com.murzify.gosporttest.core.database.model.MealEntity
import com.murzify.gosporttest.core.domain.model.Category
import com.murzify.gosporttest.core.domain.model.Meal
import com.murzify.gosporttest.core.network.model.CategoriesDto
import com.murzify.gosporttest.core.network.model.CategoryDto
import com.murzify.gosporttest.core.network.model.MealDto
import com.murzify.gosporttest.core.network.model.MealsDto

internal fun CategoryEntity.toCategory() = Category(id, name)

internal fun List<CategoryEntity>.toCategories() = map { entities -> entities.toCategory() }

internal fun CategoryDto.toEntity() = CategoryEntity(idCategory, strCategory)

internal fun CategoriesDto.toEntities() = categories.map { dto -> dto.toEntity() }

internal fun MealDto.toEntity() = MealEntity(
    idMeal,
    strMeal,
    strCategory,
    strMealThumb,
    strInstructions
)

internal fun MealsDto.toEntities() = meals.map { dto -> dto.toEntity() }

internal fun MealAndCategory.toMeal() = Meal(
    meal.id,
    meal.name,
    category.toCategory(),
    meal.thumb,
    meal.instructions
)

internal fun List<MealAndCategory>.toMeals() = map { entities -> entities.toMeal() }

