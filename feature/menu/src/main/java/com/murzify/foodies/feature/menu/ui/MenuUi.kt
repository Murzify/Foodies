package com.murzify.foodies.feature.menu.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.murzify.foodies.feature.menu.components.MenuComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MenuUi(component: MenuComponent) {
    val categories by component.categories.collectAsState()
    val selectedCategory by component.selectedCategory.collectAsState()
    val meals by component.meals.collectAsState()
    val showProgress by component.showProgress.collectAsState()

    Column {
        CustomToolbar()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Banners()
            }
            stickyHeader {
                Categories(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onSelect = component::selectCategory
                )
            }
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
                MealCard(meal = meal)
            }
        }
    }

}

