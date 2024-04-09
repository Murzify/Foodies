package com.murzify.gosporttest.core.data

import android.util.Log
import com.murzify.gosporttest.core.database.dao.CategoryDao
import com.murzify.gosporttest.core.database.dao.MealDao
import com.murzify.gosporttest.core.domain.model.Category
import com.murzify.gosporttest.core.domain.model.Meal
import com.murzify.gosporttest.core.domain.repository.MealRepository
import com.murzify.gosporttest.core.network.NetworkMonitor
import com.murzify.gosporttest.core.network.api.TheMealDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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