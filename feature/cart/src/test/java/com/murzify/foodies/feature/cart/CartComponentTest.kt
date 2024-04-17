package com.murzify.foodies.feature.cart

import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.CartItem
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.CartRepository
import com.murzify.foodies.feature.cart.components.CartComponent
import com.murzify.foodies.feature.cart.components.DefaultCartComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CartComponentTest {

    private lateinit var cartComponent: CartComponent
    private val componentContext = mock<ComponentContext>()
    private val cartRepository = mock<CartRepository>()
    private val navigateBack = mock<() -> Unit> {
        on { invoke() } doReturn (Unit)
    }

    @Before
    fun setup() {
        Mockito.reset(componentContext)
        Mockito.reset(cartRepository)
        cartComponent = DefaultCartComponent(componentContext, navigateBack, cartRepository)
    }

    @Test
    fun `should invoke add method`() {
        cartComponent.addToCart(fakeProduct)
        Mockito.verify(cartRepository).add(fakeProduct)
    }

    @Test
    fun `should invoke remove method`() {
        cartComponent.removeFromCart(fakeProduct)
        Mockito.verify(cartRepository).remove(fakeProduct)
    }

    @Test
    fun `correctly calculates cart sum`() = runTest {
        val fakeCartRepository = FakeCartRepository()
        cartComponent = DefaultCartComponent(componentContext, {}, fakeCartRepository)
        val actual = cartComponent.cartSum.first()
        val expect =
            fakeCartRepository.cart.first().items.sumOf { it.product.priceCurrent * it.amount }
        Assert.assertEquals(expect, actual)
    }

    @Test
    fun `navigation icon should invoke navigateBack method`() {
        cartComponent.onNavigationIconClick()
        Mockito.verify(navigateBack).invoke()
    }

    @Test
    fun `should show order button`() = runTest {
        val fakeCartRepository = FakeCartRepository()
        cartComponent = DefaultCartComponent(componentContext, navigateBack, fakeCartRepository)
        val actual = cartComponent.showOrderButton.first()
        Assert.assertEquals(true, actual)
    }

    @Test
    fun `should hide order button`() = runTest {
        val fakeCartRepository = FakeCartRepository()
        fakeCartRepository.emptyCart()
        cartComponent = DefaultCartComponent(componentContext, {}, fakeCartRepository)
        val actual = cartComponent.showOrderButton.first()
        Assert.assertEquals(false, actual)
    }
}

class FakeCartRepository : CartRepository {
    override val cart = MutableStateFlow(
        Cart(
            items = listOf(
                CartItem(fakeProduct, 3),
                CartItem(
                    fakeProduct.copy(id = 2L, priceCurrent = 10000),
                    amount = 2
                )
            )
        )
    )

    fun emptyCart() {
        cart.value = Cart(items = emptyList())
    }

    override fun add(product: Product) {

    }

    override fun remove(product: Product) {

    }

}