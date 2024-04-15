package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.ui.ProductCounter
import com.valentinilk.shimmer.shimmer
import java.text.NumberFormat
import java.util.Currency

@Composable
internal fun ProductCard(
    product: Product,
    cart: Cart,
    add: () -> Unit,
    remove: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .aspectRatio(0.5f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = Color.Black
        )
    ) {
        Box {
            Image(
                painter = painterResource(id = com.murzify.foodies.core.ui.R.drawable.product),
                contentDescription = ""
            )
            ProductTags(tags = product.tags)
        }

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
                ProductCounter(
                    modifier = Modifier.fillMaxWidth(),
                    amount = cartItem.amount,
                    plus = add,
                    minus = remove
                )
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

@Composable
fun ProductPlaceholder() {
    Card(
        shape = RoundedCornerShape(8),
        modifier = Modifier
            .aspectRatio(0.5f),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = Color.Black
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmer()
                .background(Color.LightGray)
        )
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