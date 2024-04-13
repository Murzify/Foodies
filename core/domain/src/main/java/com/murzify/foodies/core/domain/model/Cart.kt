package com.murzify.foodies.core.domain.model

data class Cart(val items: List<CartItem>)

data class CartItem(
    val product: Product,
    val amount: Int
)
