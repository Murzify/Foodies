package com.murzify.foodies.core.data

import com.murzify.foodies.core.database.dao.CategoryDao
import com.murzify.foodies.core.database.dao.MealDao
import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Meal
import com.murzify.foodies.core.domain.repository.MealRepository
import com.murzify.foodies.core.network.NetworkMonitor
import com.murzify.foodies.core.network.api.TheMealDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDbApi: TheMealDb,
    private val categoryDao: CategoryDao,
    private val mealDao: MealDao,
    private val netMonitor: NetworkMonitor
) : MealRepository {

    override suspend fun loadMeals() {
        netMonitor.doOnAvailable {
            val meals = mealDbApi.search().toEntities().toTypedArray()
            mealDao.insert(*meals)
        }
    }

    override suspend fun loadCategories() {
        netMonitor.doOnAvailable {
            val categories = mealDbApi.categories().toEntities().toTypedArray()
            categoryDao.insert(*categories)
        }
    }

    override suspend fun getMealsByCategory(category: Category): Flow<List<Meal>> {
        return mealDao.getByCategory(category.name).map { mealEntities -> mealEntities.toMeals() }
    }

    override suspend fun getCategories(): Flow<List<Category>> {
        val cached = categoryDao.getAll().map { categoryEntities -> categoryEntities.toCategories() }
        return cached
    }

}