package com.murzify.foodies.feature.catalog.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.common.componentCoroutineScope
import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Meal
import com.murzify.foodies.core.domain.repository.MealRepository
import com.murzify.foodies.feature.catalog.ui.CatalogUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DefaultCatalogComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    private val mealRepository: MealRepository
) : CatalogComponent, ComponentContext by componentContext {

    override val categories = MutableStateFlow<List<Category>>(emptyList())
    override val selectedCategory = MutableStateFlow<Category?>(null)
    override val meals = MutableStateFlow<List<Meal>>(emptyList())
    override val showProgress = MutableStateFlow(false)

    private val scope = componentContext.componentCoroutineScope()

    init {
        scope.launch(Dispatchers.IO) {
            mealRepository.loadMeals()
        }

        scope.launch(Dispatchers.IO) {
            mealRepository.loadCategories()
        }

        scope.launch(Dispatchers.IO) {
            mealRepository.getCategories().collect {
                categories.value = it
                selectedCategory.value = it.firstOrNull()
            }
        }

        var mealsJob: Job? = null
        scope.launch(Dispatchers.IO) {
            selectedCategory.collect { category ->
                mealsJob?.cancel()
                mealsJob = category?.collectMeals()
            }
        }

        scope.launch {
            meals.collect {
                showProgress.value = it.isEmpty()
            }
        }

    }

    private fun Category.collectMeals() = scope.launch(Dispatchers.IO) {
        mealRepository.getMealsByCategory(this@collectMeals).collect { newMeals ->
            meals.value = newMeals
        }
    }

    override fun selectCategory(category: Category) {
        selectedCategory.value = category
    }

    @Composable
    override fun Render() {
        CatalogUi(this)
    }

    @AssistedFactory
    interface Factory : CatalogComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ): DefaultCatalogComponent
    }

}