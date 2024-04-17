package com.murzify.foodies.core.domain.repository

import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.Product
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    val cart: StateFlow<Cart>

    /**
     * Add one item to the cart
     */
    fun add(product: Product)

    /**
     * Remove one item from the cart
     */
    fun remove(product: Product)

}