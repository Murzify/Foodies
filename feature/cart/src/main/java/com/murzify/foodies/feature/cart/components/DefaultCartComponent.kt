package com.murzify.foodies.feature.cart.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.CartRepository
import com.murzify.foodies.feature.cart.ui.CartUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.map

class DefaultCartComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val navigateBack: () -> Unit,
    private val cartRepository: CartRepository
) : CartComponent, ComponentContext by componentContext {
    override val cart = cartRepository.cart
    override val cartSum = cart.map { cart ->
        cart.items.fold(0L) { acc, item ->
            acc + item.product.priceCurrent * item.amount
        }
    }
    override val showOrderButton = cartSum.map { it > 0 }
    override val cartIsEmpty = cart.map { it.items.isEmpty() }


    override fun onNavigationIconClick() {
        navigateBack()
    }

    override fun addToCart(product: Product) {
        cartRepository.add(product)
    }

    override fun removeFromCart(product: Product) {
        cartRepository.remove(product)
    }

    @Composable
    override fun Render() {
        CartUi(this)
    }

    @AssistedFactory
    interface Factory : CartComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            navigateBack: () -> Unit
        ): DefaultCartComponent
    }
}