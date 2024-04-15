package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.murzify.foodies.core.domain.model.Tag
import com.murzify.foodies.feature.menu.R

@Composable
fun ProductTags(tags: List<Tag>) {
    val icons = mapOf(
        1 to R.drawable.ic_discount_tag,
        2 to R.drawable.ic_vegetarian_tag,
        3 to R.drawable.ic_discount_tag,
        4 to R.drawable.ic_spicy_tag,
        5 to R.drawable.ic_discount_tag
    )
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        tags.forEach { tag ->
            icons[tag.id]?.let { iconId ->
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = tag.name,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}