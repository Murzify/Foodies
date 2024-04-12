package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.murzify.foodies.feature.catalog.components.CatalogComponent

@Composable
internal fun CatalogUi(component: CatalogComponent) {
    val categories by component.categories.collectAsState()
    val selectedCategory by component.selectedCategory.collectAsState()
    val meals by component.products.collectAsState()

    Column {
        CustomToolbar()
        Categories(
            categories = categories,
            selectedCategory = selectedCategory,
            onSelect = component::selectCategory
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                count = if (meals.isEmpty()) 5 else 0
            ) {
                HorizontalDivider()
                PlaceHolderMealCard()
            }
            items(
                meals,
                key = { it.id }
            ) { meal ->
                HorizontalDivider()
                MealCard(product = meal)
            }
        }
    }

}

