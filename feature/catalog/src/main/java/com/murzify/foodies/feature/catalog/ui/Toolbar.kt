package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.murzify.foodies.feature.menu.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Toolbar(scrollBehavior: TopAppBarScrollBehavior) {

    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            scrolledContainerColor = Color.White
        ),
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "filter",
                    modifier = Modifier.padding(8.dp),
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    tint = Color.Black
                )
            }
        }
    )
}