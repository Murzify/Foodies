package com.murzify.foodies.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, backgroundColor = 0xFFCCCCCC)
@Composable
fun ProductCounter(
    modifier: Modifier = Modifier,
    amount: Int = 3,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary
    ),
    plus: () -> Unit = {},
    minus: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        CountButton(iconId = R.drawable.ic_minus, buttonColors, minus)
        Text(
            text = amount.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        CountButton(iconId = R.drawable.ic_plus, buttonColors, plus)
    }
}

@Composable
private fun CountButton(@DrawableRes iconId: Int, colors: ButtonColors, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = colors,
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