package com.murzify.foodies.core.data

import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.CartItem
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.model.Tag
import com.murzify.foodies.core.domain.repository.CartRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CartRepositoryTest {
    private lateinit var cartRepository: CartRepository

    private val fakeProduct = Product(
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
        tags = listOf(Tag(1, "Discount"))
    )
    private val fakeCartItem = CartItem(fakeProduct, 1)
    private val fakeCart = Cart(items = listOf(fakeCartItem))

    @Before
    fun setup() {
        cartRepository = CartRepositoryImpl()
    }

    @Test
    fun `should add product to the StateFlow`() = runTest {
        cartRepository.add(fakeProduct)
        val actual = cartRepository.cart.first()
        Assert.assertEquals(fakeCart, actual)
    }

    @Test
    fun `when product already in cart increment it`() = runTest {
        cartRepository.add(fakeProduct)
        val initialCart = cartRepository.cart.first()
        cartRepository.add(fakeProduct)
        val incrementedCart = initialCart.copy(
            items = initialCart.items.map { cartItem ->
                if (cartItem.product.id == fakeProduct.id) {
                    cartItem.copy(
                        amount = cartItem.amount + 1
                    )
                } else {
                    cartItem
                }
            }
        )

        val actual = cartRepository.cart.first()
        Assert.assertEquals(incrementedCart, actual)
    }

    @Test
    fun `remove cart item if amount is zero`() = runTest {
        cartRepository.add(fakeProduct)
        val initialCart = cartRepository.cart.first()
        val items = initialCart.items.toMutableList()
        items.remove(fakeCartItem)
        val emptyCart = initialCart.copy(items = items)
        cartRepository.removeItem(fakeProduct)
        val actual = cartRepository.cart.first()
        Assert.assertEquals(emptyCart, actual)
    }

    @Test
    fun `decrement cart item`() = runTest {
        cartRepository.add(fakeProduct)
        cartRepository.add(fakeProduct)
        val initialCart = cartRepository.cart.first()

        val decrementedCart = initialCart.copy(
            items = initialCart.items.map { cartItem ->
                if (cartItem.product.id == fakeProduct.id) {
                    cartItem.copy(
                        amount = cartItem.amount - 1
                    )
                } else {
                    cartItem
                }
            }
        )

        cartRepository.removeItem(fakeProduct)
        val actual = cartRepository.cart.first()

        Assert.assertEquals(decrementedCart, actual)
    }
}