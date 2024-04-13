package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.murzify.foodies.feature.catalog.components.CatalogComponent

@Composable
internal fun CatalogUi(component: CatalogComponent) {
    val categories by component.categories.collectAsState()
    val selectedCategory by component.selectedCategory.collectAsState()
    val filteredProducts by component.filteredProducts.collectAsState(emptyList())
    val cart by component.cart.collectAsState()

    Column {
        CustomToolbar()
        Categories(
            categories = categories,
            selectedCategory = selectedCategory,
            onSelect = component::selectCategory
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                filteredProducts,
                key = { it.id }
            ) { product ->
                Row(
                    modifier = Modifier.height(IntrinsicSize.Max)
                ) {
                    ProductCard(
                        product = product,
                        cart,
                        add = { component.addToCart(product) },
                        remove = { component.removeFromCart(product) }
                    )
                }
            }
        }
    }

}

