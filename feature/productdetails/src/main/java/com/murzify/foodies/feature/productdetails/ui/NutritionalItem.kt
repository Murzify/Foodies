package com.murzify.foodies.feature.productdetails.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
internal fun NutritionalItem(
    @StringRes titleId: Int,
    value: Float,
    @StringRes valueUnitId: Int
) {
    HorizontalDivider()
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = titleId),
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = stringResource(id = valueUnitId, valueFormat(value)),
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

private fun valueFormat(value: Float) = if (value % 1 == 0f) {
    value.toInt().toString()
} else {
    String.format("%.1f", value)
}