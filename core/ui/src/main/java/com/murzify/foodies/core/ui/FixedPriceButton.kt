package com.murzify.foodies.core.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Currency

@Composable
fun FixedPriceButton(
    price: Long,
    prefix: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val rub = Currency.getInstance("RUB")
    val priceFormat = NumberFormat.getInstance()
    priceFormat.apply {
        maximumFractionDigits = 0
    }
    Box(modifier = Modifier.background(Color.White)) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp),
            contentPadding = PaddingValues(14.dp)
        ) {
            prefix()
            AnimatedContent(
                targetState = price,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { -it } togetherWith slideOutVertically { it }
                    } else {
                        slideInVertically { it } togetherWith slideOutVertically { -it }
                    }
                },
                label = ""
            ) { price ->
                Text(text = priceFormat.format(price / 100))
            }
            Text(text = " ${rub.symbol}")
        }
    }

}