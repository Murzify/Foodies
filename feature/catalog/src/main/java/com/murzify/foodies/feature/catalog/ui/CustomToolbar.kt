package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
        IconButton(
            modifier = Modifier.padding(8.dp),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "filter",
                modifier = Modifier.padding(8.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
        )

        IconButton(
            modifier = Modifier.padding(8.dp),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search",
            )
        }

    }
}