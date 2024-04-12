package com.murzify.foodies.core.domain.repository

import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product

interface ProductsRepository {
    suspend fun getCategories(): List<Category>

    suspend fun getProducts(): List<Product>
}