package com.murzify.foodies.feature.catalog.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Meal
import kotlinx.coroutines.flow.StateFlow

interface MenuComponent {

    val categories: StateFlow<List<Category>>
    val selectedCategory: StateFlow<Category?>
    val meals: StateFlow<List<Meal>>
    val showProgress: StateFlow<Boolean>

    fun selectCategory(category: Category)

    @Composable
    fun Render()

    interface Factory {
        operator fun invoke(componentContext: ComponentContext): MenuComponent
    }
}