package com.murzify.foodies.feature.cart

import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.model.Tag

val fakeTags = listOf(Tag(1, "Discount"))
val fakeProduct = Product(
    1L,
    2L,
    "test",
    "test test",
    "1.jpg",
    2000L,
    5000L,
    33,
    "г",
    20.54f,
    13.4f,
    56.3f,
    12.6f,
    tags = fakeTags
)