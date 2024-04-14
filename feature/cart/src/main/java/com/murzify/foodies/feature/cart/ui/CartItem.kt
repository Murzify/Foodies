package com.murzify.foodies.feature.cart.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murzify.foodies.core.domain.model.CartItem
import com.murzify.foodies.core.ui.ProductCounter
import com.murzify.foodies.core.ui.R
import java.text.NumberFormat
import java.util.Currency

@Composable
internal fun CartItem(
    cartItem: CartItem,
    add: () -> Unit,
    remove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.product),
            contentDescription = cartItem.product.name,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(96.dp)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.defaultMinSize(minHeight = 96.dp)
        ) {
            Text(
                text = cartItem.product.name,
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                ProductCounter(
                    modifier = Modifier.weight(3f),
                    amount = cartItem.amount,
                    buttonColors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    plus = add,
                    minus = remove
                )
                Column(
                    modifier = Modifier.weight(2f),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    val rub = Currency.getInstance("RUB")
                    val priceFormat = NumberFormat.getCurrencyInstance()
                    priceFormat.apply {
                        maximumFractionDigits = 0
                        currency = rub
                    }

                    Text(
                        text = priceFormat.format(cartItem.product.priceCurrent / 100),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )

                    val priceOld = cartItem.product.priceOld
                    if (priceOld != null) {
                        Text(
                            text = priceFormat.format(priceOld / 100),
                            style = TextStyle(
                                textDecoration = TextDecoration.LineThrough,
                                fontSize = 14.sp,
                                color = Color.Gray
                            ),
                            textAlign = TextAlign.End
                        )
                    }

                }
            }
        }
    }
    HorizontalDivider()
}