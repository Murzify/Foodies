package com.murzify.foodies.feature.cart.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CartComponent {

    val cart: StateFlow<Cart>
    val cartSum: Flow<Long>
    val showOrderButton: Flow<Boolean>

    fun onNavigationIconClick()

    fun addToCart(product: Product)

    fun removeFromCart(product: Product)

    @Composable
    fun Render()

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateBack: () -> Unit
        ): CartComponent
    }
}