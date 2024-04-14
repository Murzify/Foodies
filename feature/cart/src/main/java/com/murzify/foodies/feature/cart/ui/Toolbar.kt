package com.murzify.foodies.feature.cart.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.murzify.foodies.feature.cart.R
import com.murzify.foodies.core.ui.R as UiR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Toolbar(onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.cart_title),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(id = UiR.drawable.ic_arrow_left),
                    contentDescription = "back",
                )
            }
        },
        windowInsets = WindowInsets(0, 0, 0, 0)
    )
}