package com.murzify.foodies.feature.cart.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.murzify.foodies.core.ui.FixedPriceButton
import com.murzify.foodies.feature.cart.R
import com.murzify.foodies.feature.cart.components.CartComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CartUi(component: CartComponent) {
    val cart by component.cart.collectAsState()
    val cartSum by component.cartSum.collectAsState(initial = 0)
    val showOrderButton by component.showOrderButton.collectAsState(initial = false)
    val cartIsEmpty by component.cartIsEmpty.collectAsState(initial = false)

    Column {
        Toolbar(component::onNavigationIconClick)
        if (cartIsEmpty) {
            EmptyCart()
        } else {
            LazyColumn {
                items(cart.items) { item ->
                    CartItem(
                        cartItem = item,
                        add = {
                            component.addToCart(item.product)
                        },
                        remove = {
                            component.removeFromCart(item.product)
                        }
                    )
                }
            }

        }
    }

    if (showOrderButton) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            FixedPriceButton(
                price = cartSum,
                prefix = {
                    Text(
                        text = stringResource(id = R.string.order),
                        modifier = Modifier.padding(end = 4.dp)
                    )
                },
                onClick = {}
            )
        }
    }

}