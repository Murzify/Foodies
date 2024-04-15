package com.murzify.foodies

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack

@Composable
internal fun RootUi(
    childStack: ChildStack<*, RootComponent.Child>,
    modifier: Modifier,
) {
    Children(
        modifier = modifier,
        stack = childStack,
        animation = stackAnimation(fade())
    ) {
        when (val instance = it.instance) {
            is RootComponent.Child.Catalog -> instance.component.Render()
            is RootComponent.Child.Cart -> instance.component.Render()
            is RootComponent.Child.ProductDetails -> instance.component.Render()
        }
    }
}