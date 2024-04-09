package com.murzify.gosporttest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.murzify.gosporttest.feature.menu.components.MenuComponent

interface RootComponent {

    @Composable
    fun Render(modifier: Modifier)

    interface Factory {
        operator fun invoke(componentContext: ComponentContext): RootComponent
    }

    sealed interface Child {
        data class Menu(val component: MenuComponent) : Child
    }
}