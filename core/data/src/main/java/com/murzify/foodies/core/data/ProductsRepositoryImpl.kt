package com.murzify.foodies.core.data

import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.ProductsRepository
import com.murzify.foodies.core.network.NetworkMonitor
import com.murzify.foodies.core.network.api.WorkTestServer
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val workTestServer: WorkTestServer,
    private val netMonitor: NetworkMonitor
) : ProductsRepository {
    override suspend fun getCategories(): List<Category> = netMonitor.doOnAvailable {
        workTestServer.categories().toCategories()
    }

    override suspend fun getProducts(): List<Product> = netMonitor.doOnAvailable {
        val productsDto = workTestServer.products()
        val tags = workTestServer.tags()
        productsDto.toProducts(tags)
    }

}