package com.murzify.foodies.feature.catalog.components

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.murzify.foodies.core.common.componentCoroutineScope
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
import kotlinx.coroutines.launch

class DefaultCatalogComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    private val productsRepository: ProductsRepository
) : CatalogComponent, ComponentContext by componentContext {

    override val categories = MutableStateFlow<List<Category>>(emptyList())
    override val selectedCategory = MutableStateFlow<Category?>(null)
    override val products = MutableStateFlow<List<Product>>(emptyList())
    override val filteredProducts = combine(selectedCategory, products) { category, products ->
        products.filter { it.categoryId == category?.id }
    }
    override val showProgress = MutableStateFlow(false)

    private val scope = componentContext.componentCoroutineScope()

    init {

        scope.launch(Dispatchers.IO) {
            categories.value = productsRepository.getCategories()
        }

        scope.launch(Dispatchers.IO) {
            products.value = productsRepository.getProducts()
        }

        scope.launch {
            products.collect {
                showProgress.value = it.isEmpty()
            }
        }

    }

    override fun selectCategory(category: Category) {
        selectedCategory.value = category
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