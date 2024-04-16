package com.murzify.foodies.core.data

import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.model.Tag
import com.murzify.foodies.core.network.model.ProductDto
import com.murzify.foodies.core.network.model.TagDto

val fakeTags = listOf(Tag(1, "Discount"))

val fakeTagDtos = listOf(TagDto(1, "Discount"))

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

val fakeProductDto = ProductDto(
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
    tagIds = listOf(1)
)