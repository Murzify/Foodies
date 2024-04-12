package com.murzify.foodies.feature.catalog.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

interface CatalogComponent {

    val categories: StateFlow<List<Category>>
    val selectedCategory: StateFlow<Category?>
    val products: StateFlow<List<Product>>
    val filteredProducts: Flow<List<Product>>
    val showProgress: StateFlow<Boolean>

    fun selectCategory(category: Category)

    @Composable
    fun Render()

    interface Factory {
        operator fun invoke(componentContext: ComponentContext): CatalogComponent
    }

    @Serializable
    data class State(
        val categories: List<Category> = emptyList(),
        val selectedCategory: Category? = null,
        val products: List<Product> = emptyList()
    )
}