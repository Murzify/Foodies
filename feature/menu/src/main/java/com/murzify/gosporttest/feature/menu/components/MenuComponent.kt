package com.murzify.gosporttest.feature.menu.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.gosporttest.core.domain.model.Category
import com.murzify.gosporttest.core.domain.model.Meal
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