package com.murzify.foodies.feature.catalog.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CatalogComponent {

    val categories: StateFlow<List<Category>>
    val selectedCategory: StateFlow<Category?>
    val products: StateFlow<List<Product>>
    val filteredProducts: Flow<List<Product>>
    val cart: StateFlow<Cart>
    val cartSum: Flow<Long>
    val showPlaceholder: Flow<Boolean>

    fun selectCategory(category: Category)

    fun addToCart(product: Product)

    fun removeFromCart(product: Product)

    fun onCartClick()

    fun onProductClick(product: Product)

    @Composable
    fun Render()

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToCart: () -> Unit,
            navigateToDetails: (Product) -> Unit
        ): CatalogComponent
    }
}