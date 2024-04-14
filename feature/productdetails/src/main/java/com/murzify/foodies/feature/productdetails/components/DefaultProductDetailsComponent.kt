package com.murzify.foodies.feature.productdetails.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.CartRepository
import com.murzify.foodies.feature.productdetails.ui.ProductDetailsUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultProductDetailsComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted override val product: Product,
    @Assisted("back") private val navigateBack: () -> Unit,
    @Assisted("cart") private val navigateToCart: () -> Unit,
    private val cartRepository: CartRepository,
) : ProductDetailsComponent, ComponentContext by componentContext {
    override fun onBackClick() {
        navigateBack()
    }

    override fun onAddToCartClick() {
        cartRepository.add(product)
        navigateToCart()
    }

    @Composable
    override fun Render() {
        ProductDetailsUi(this)
    }

    @AssistedFactory
    interface Factory : ProductDetailsComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            product: Product,
            @Assisted("back") navigateBack: () -> Unit,
            @Assisted("cart") navigateToCart: () -> Unit
        ): DefaultProductDetailsComponent
    }
}