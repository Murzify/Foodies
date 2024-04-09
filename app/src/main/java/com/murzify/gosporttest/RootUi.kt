package com.murzify.gosporttest

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack

@Composable
internal fun RootUi(
    childStack: ChildStack<*, RootComponent.Child>,
    modifier: Modifier,
    selectedTab: BottomBarEnum,
    onSelect: (BottomBarEnum) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomBarEnum.entries.forEach { tab ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                        selected = tab == selectedTab,
                        onClick = {
                            onSelect(tab) 
                        },
                        icon = { 
                            Icon(
                                painter = painterResource(id = tab.iconId),
                                contentDescription = stringResource(id = tab.tabName)
                            )
                        },
                        label = {
                            Text(stringResource(id = tab.tabName))
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Children(
            stack = childStack,
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Menu -> instance.component.Render()
            }
        }
    }
}