package com.murzify.foodies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.murzify.foodies.feature.catalog.components.CatalogComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class DefaultRootComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    val catalogComponentFactory: CatalogComponent.Factory
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Menu,
        childFactory = ::child
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
        Config.Menu -> RootComponent.Child.Catalog(
            catalogComponentFactory(componentContext)
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
        data object Menu : Config
    }

}