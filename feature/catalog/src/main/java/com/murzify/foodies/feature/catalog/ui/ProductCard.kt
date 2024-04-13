package com.murzify.foodies.feature.catalog.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.feature.menu.R
import java.text.NumberFormat
import java.util.Currency

@Composable
internal fun ProductCard(
    product: Product,
    cart: Cart,
    add: () -> Unit,
    remove: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8),
        modifier = Modifier.aspectRatio(0.5f),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = Color.Black
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.product),
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (product.measure != 0) {
                    Text(
                        text = "${product.measure} ${product.measureUnit}",
                        fontSize = 14.sp
                    )
                }
            }

            val cartItem = cart.items.firstOrNull { it.product.id == product.id }

            if (cartItem != null) { // product in cart
                ProductCounter(cartItem.amount, plus = add, minus = remove)
            } else {
                AddToCart(
                    priceCurrent = product.priceCurrent,
                    priceOld = product.priceOld,
                    onClick = add
                )
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColumnScope.AddToCart(priceCurrent: Long, priceOld: Long?, onClick: () -> Unit) {
    val rub = Currency.getInstance("RUB")
    val priceFormat = NumberFormat.getCurrencyInstance()
    priceFormat.apply {
        maximumFractionDigits = 0
        currency = rub
    }
    val price = priceCurrent / 100f
    Button(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        FlowRow {
            Text(
                text = priceFormat.format(price),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            if (priceOld != null) {
                Text(
                    text = priceFormat.format(priceOld / 100),
                    style = TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = 14.sp,
                        color = Color.Gray
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

    }
}

@Preview
@Composable
private fun ProductCounter(
    amount: Int = 3,
    plus: () -> Unit = {},
    minus: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        CountButton(iconId = R.drawable.ic_minus, minus)
        Text(
            text = amount.toString(),
            modifier = Modifier.padding(12.dp)
        )
        CountButton(iconId = R.drawable.ic_plus, plus)
    }
}

@Composable
private fun CountButton(@DrawableRes iconId: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null
        )
    }
}