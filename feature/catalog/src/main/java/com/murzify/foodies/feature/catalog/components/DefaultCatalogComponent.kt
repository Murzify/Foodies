package com.murzify.foodies.feature.catalog.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.common.componentCoroutineScope
import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.CartRepository
import com.murzify.foodies.core.domain.repository.ProductsRepository
import com.murzify.foodies.feature.catalog.ui.CatalogUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DefaultCatalogComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted("cart") private val navigateToCart: () -> Unit,
    @Assisted("details") private val navigateToDetails: (Product) -> Unit,
    private val productsRepository: ProductsRepository,
    private val cartRepository: CartRepository,
) : CatalogComponent, ComponentContext by componentContext {
    private val scope = componentContext.componentCoroutineScope()

    override val categories = MutableStateFlow<List<Category>>(emptyList())
    override val selectedCategory = MutableStateFlow<Category?>(null)
    override val products = MutableStateFlow<List<Product>>(emptyList())
    override val filteredProducts = combine(selectedCategory, products) { category, products ->
        products.filter { it.categoryId == category?.id }
    }
    override val cart = cartRepository.cart
    override val cartSum = cart.map { cart ->
        cart.items.fold(0L) { acc, item ->
            acc + item.product.priceCurrent * item.amount
        }
    }
    override val showPlaceholder = combine(categories, products) { categories, products ->
        categories.isEmpty() || products.isEmpty()
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
        cartRepository.add(product)
    }

    override fun removeFromCart(product: Product) {
        cartRepository.removeItem(product)
    }

    override fun onCartClick() {
        navigateToCart()
    }

    override fun onProductClick(product: Product) {
        navigateToDetails(product)
    }

    @Composable
    override fun Render() {
        CatalogUi(this)
    }

    @AssistedFactory
    interface Factory : CatalogComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            @Assisted("cart") navigateToCart: () -> Unit,
            @Assisted("details") navigateToDetails: (Product) -> Unit
        ): DefaultCatalogComponent
    }

}