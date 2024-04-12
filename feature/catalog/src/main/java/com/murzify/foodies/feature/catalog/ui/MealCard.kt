package com.murzify.foodies.feature.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murzify.foodies.core.domain.model.Product
import com.valentinilk.shimmer.shimmer

@Composable
internal fun MealCard(product: Product) {
    Row(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .padding(end = 22.dp)
                .size(135.dp),

            ) {
//            AsyncImage(
//                model = product.thumb,
//                contentDescription = product.name,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
        }
        Column(
            modifier = Modifier.height(135.dp)
        ) {
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = product.description,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
internal fun PlaceHolderMealCard() {
    Row(modifier = Modifier
        .padding(16.dp)
        .shimmer()) {
        Card(
            modifier = Modifier
                .padding(end = 22.dp)
                .size(135.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {}
        Column(
            modifier = Modifier
                .height(135.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier
                .height(32.dp)
                .fillMaxWidth()
                .padding(end = 32.dp, bottom = 8.dp)
                .background(Color.LightGray)
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
            )
        }
    }
}