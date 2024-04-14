package com.murzify.foodies

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.feature.cart.components.CartComponent
import com.murzify.foodies.feature.catalog.components.CatalogComponent

interface RootComponent {

    @Composable
    fun Render(modifier: Modifier)

    interface Factory {
        operator fun invoke(componentContext: ComponentContext): RootComponent
    }

    sealed interface Child {
        data class Catalog(val component: CatalogComponent) : Child

        data class Cart(val component: CartComponent) : Child
    }
}