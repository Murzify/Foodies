package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murzify.foodies.core.domain.model.Category
import com.valentinilk.shimmer.shimmer

@Composable
internal fun Categories(
    categories: List<Category>,
    selectedCategory: Category?,
    showPlaceholder: Boolean,
    onSelect: (Category) -> Unit
) {
    if (showPlaceholder) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .scrollable(rememberScrollState(), orientation = Orientation.Horizontal)
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            repeat(8) {
                CategoryPlaceholder()
            }
        }
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                CategoryCard(text = category.name, selectedCategory == category) {
                    onSelect(category)
                }
            }
        }
    }

}

@Composable
private fun CategoryCard(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.White
    val textColor = if (selected) Color.White else Color.Black
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(40.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Text(
                text = text,
                fontSize = 13.sp,
                modifier = Modifier.padding(horizontal = 24.dp),
                color = textColor
            )
        }
    }
}

@Composable
private fun CategoryPlaceholder() {
    ElevatedCard(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .size(88.dp, 32.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .shimmer()
            .background(Color.LightGray))
    }
}