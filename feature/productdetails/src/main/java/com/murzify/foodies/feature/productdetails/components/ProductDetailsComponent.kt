package com.murzify.foodies.feature.productdetails.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Product

interface ProductDetailsComponent {

    val product: Product

    fun onBackClick()

    fun onAddToCartClick()

    @Composable
    fun Render()

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            product: Product,
            navigateBack: () -> Unit,
            navigateToCart: () -> Unit
        ): ProductDetailsComponent
    }
}