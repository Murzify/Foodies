package com.murzify.foodies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack

@Composable
internal fun RootUi(
    childStack: ChildStack<*, RootComponent.Child>,
    modifier: Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Children(
            stack = childStack,
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Catalog -> instance.component.Render()
            }
        }
    }
}