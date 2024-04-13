package com.murzify.foodies.feature.catalog.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.common.componentCoroutineScope
import com.murzify.foodies.core.domain.model.Cart
import com.murzify.foodies.core.domain.model.CartItem
import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.ProductsRepository
import com.murzify.foodies.feature.catalog.ui.CatalogUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DefaultCatalogComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    private val productsRepository: ProductsRepository
) : CatalogComponent, ComponentContext by componentContext {
    private val scope = componentContext.componentCoroutineScope()

    override val categories = MutableStateFlow<List<Category>>(emptyList())
    override val selectedCategory = MutableStateFlow<Category?>(null)
    override val products = MutableStateFlow<List<Product>>(emptyList())
    override val filteredProducts = combine(selectedCategory, products) { category, products ->
        products.filter { it.categoryId == category?.id }
    }
    override val cart = MutableStateFlow(Cart(emptyList()))
    override val cartSum = cart.map { cart ->
        cart.items.fold(0L) { acc, item ->
            acc + item.product.priceCurrent * item.amount
        }
    }

    init {
        scope.launch(Dispatchers.IO) {
            categories.value = productsRepository.getCategories()
            selectedCategory.value = categories.value.first()
        }

        scope.launch(Dispatchers.IO) {
            products.value = productsRepository.getProducts()
        }

    }

    override fun selectCategory(category: Category) {
        selectedCategory.value = category
    }

    override fun addToCart(product: Product) {
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

    override fun removeFromCart(product: Product) {
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

    @Composable
    override fun Render() {
        CatalogUi(this)
    }

    @AssistedFactory
    interface Factory : CatalogComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ): DefaultCatalogComponent
    }

}