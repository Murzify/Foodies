package com.murzify.foodies.feature.menu.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.murzify.foodies.feature.menu.R

@Preview
@Composable
internal fun Banners() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .padding(vertical = 10.dp),
    ) {
        item {
            Banner(res = R.drawable.diskount_banner)
        }
        item {
            Banner(res = R.drawable.pizza_gift_banner)
        }
        item { Spacer(modifier = Modifier.width(16.dp)) }
    }
}

@Composable
private fun Banner(@DrawableRes res: Int) {
    Card(
        shape = RoundedCornerShape(10),
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Image(
            painter = painterResource(id = res),
            contentDescription = "",
        )
    }
}