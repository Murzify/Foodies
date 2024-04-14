package com.murzify.foodies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.feature.cart.components.CartComponent
import com.murzify.foodies.feature.catalog.components.CatalogComponent
import com.murzify.foodies.feature.productdetails.components.ProductDetailsComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class DefaultRootComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    val catalogComponentFactory: CatalogComponent.Factory,
    val cartComponentFactory: CartComponent.Factory,
    val productDetailsComponentFactory: ProductDetailsComponent.Factory
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Catalog,
        childFactory = ::child,
        handleBackButton = true
    )

    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()
        RootUi(childStack, modifier)
    }

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ) = when (config) {
        Config.Catalog -> RootComponent.Child.Catalog(
            catalogComponentFactory(
                componentContext,
                navigateToCart = {
                    navigation.bringToFront(Config.Cart)
                },
                navigateToDetails = { product ->
                    navigation.bringToFront(Config.ProductDetails(product))
                }
            )
        )

        Config.Cart -> RootComponent.Child.Cart(
            cartComponentFactory(componentContext, navigateBack = navigation::pop)
        )

        is Config.ProductDetails -> RootComponent.Child.ProductDetails(
            productDetailsComponentFactory(
                componentContext,
                product = config.product,
                navigateBack = navigation::pop,
                navigateToCart = {
                    navigation.bringToFront(Config.Cart)
                }
            )
        )
    }

    @AssistedFactory
    interface Factory : RootComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ): DefaultRootComponent
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Catalog : Config

        @Serializable
        data object Cart : Config

        data class ProductDetails(val product: Product) : Config
    }

}