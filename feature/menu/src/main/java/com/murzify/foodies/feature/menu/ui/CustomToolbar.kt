package com.murzify.foodies.feature.menu.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.murzify.foodies.feature.menu.R

@Preview(showBackground = true)
@Composable
internal fun CustomToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FakeCitySwitcher()
        Icon(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(id = R.drawable.ic_qr),
            contentDescription = ""
        )
    }
}

@Composable
private fun FakeCitySwitcher() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(stringResource(id = R.string.city), modifier = Modifier.padding(end = 8.dp))
        Icon(painterResource(id = R.drawable.ic_arrow_down), "")
    }
}