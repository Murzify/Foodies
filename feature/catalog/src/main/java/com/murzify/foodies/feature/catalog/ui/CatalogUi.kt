package com.murzify.foodies.feature.catalog.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.murzify.foodies.core.ui.FixedPriceButton
import com.murzify.foodies.feature.catalog.components.CatalogComponent
import com.murzify.foodies.feature.menu.R

@Composable
internal fun CatalogUi(component: CatalogComponent) {
    val categories by component.categories.collectAsState()
    val selectedCategory by component.selectedCategory.collectAsState()
    val filteredProducts by component.filteredProducts.collectAsState(emptyList())
    val cart by component.cart.collectAsState()
    val cartSum by component.cartSum.collectAsState(initial = 0L)

    Column {
        CustomToolbar()
        Categories(
            categories = categories,
            selectedCategory = selectedCategory,
            onSelect = component::selectCategory
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 88.dp
            ),
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

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = cartSum > 0,
            modifier = Modifier.background(Color.White)
        ) {
            FixedPriceButton(
                price = cartSum,
                prefix = {
                    Icon(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "cart",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                onClick = component::onCartClick
            )
        }
    }


}

