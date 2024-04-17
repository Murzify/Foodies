package com.murzify.foodies.core.data

import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.CartItem
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor() : CartRepository {
    override val cart = MutableStateFlow(Cart(emptyList()))

    override fun add(product: Product) {
        cart.update {
            val newItems = if (it.items.any { item -> item.product.id == product.id }) {
                it.items.map { item ->
                    if (item.product.id == product.id) {
                        item.copy(amount = item.amount + 1)
                    } else {
                        item
                    }
                }
            } else {
                it.items.toMutableList().apply { add(CartItem(product, 1)) }
            }
            it.copy(
                newItems
            )
        }
    }

    override fun remove(product: Product) {
        cart.update {
            val newItems = it.items.mapNotNull { item ->
                if (item.product.id == product.id) {
                    if (item.amount == 1) {
                        null
                    } else {
                        item.copy(amount = item.amount - 1)
                    }
                } else {
                    item
                }
            }
            it.copy(
                newItems
            )
        }
    }
}