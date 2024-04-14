package com.murzify.foodies.feature.productdetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murzify.foodies.core.ui.FixedPriceButton
import com.murzify.foodies.feature.productdetails.R
import com.murzify.foodies.feature.productdetails.components.ProductDetailsComponent
import com.murzify.foodies.core.ui.R as UiR

@Composable
internal fun ProductDetailsUi(component: ProductDetailsComponent) {
    val product = component.product
    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollableState)
    ) {
        Image(
            painter = painterResource(id = UiR.drawable.product),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Text(
            text = product.name,
            modifier = Modifier.padding(top = 24.dp, start = 16.dp),
            fontSize = 34.sp,
            lineHeight = 40.sp
        )
        Text(
            text = product.description,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
        )
        NutritionalItem(
            titleId = R.string.weight,
            value = product.measure.toFloat(),
            valueUnitId = R.string.gram
        )
        NutritionalItem(
            titleId = R.string.energy,
            value = product.energy,
            valueUnitId = R.string.kcal
        )
        NutritionalItem(
            titleId = R.string.proteins,
            value = product.proteins,
            valueUnitId = R.string.gram
        )
        NutritionalItem(
            titleId = R.string.fats,
            value = product.fats,
            valueUnitId = R.string.gram
        )
        NutritionalItem(
            titleId = R.string.carbohydrates,
            value = product.carbohydrates,
            valueUnitId = R.string.gram
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(88.dp))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(modifier = Modifier.background(Color.White)) {
            FixedPriceButton(
                price = product.priceCurrent,
                prefix = {
                    Text(
                        text = stringResource(id = R.string.add_to_cart),
                        modifier = Modifier.padding(end = 4.dp)
                    )
                },
                onClick = component::onAddToCartClick
            )
        }
    }

    ElevatedButton(
        onClick = component::onBackClick,
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .padding(16.dp)
            .size(44.dp),
        contentPadding = PaddingValues(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = UiR.drawable.ic_arrow_left),
            contentDescription = "back",
        )
    }
}