package com.murzify.foodies.core.data

import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.repository.ProductsRepository
import com.murzify.foodies.core.network.NetworkMonitor
import com.murzify.foodies.core.network.api.WorkTestServer
import com.murzify.foodies.core.network.model.CategoryDto
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class ProductsRepositoryTest {

    private lateinit var productsRepository: ProductsRepository
    private val netMonitor = mock<NetworkMonitor>()
    private val workTestServer = mock<WorkTestServer>()
    private val fakeCategoriesDto = List(3) {
        CategoryDto(it.toLong(), it.toString())
    }
    private val fakeCategories = List(3) {
        Category(it.toLong(), it.toString())
    }

    @Before
    fun setup() {
        Mockito.reset(netMonitor)
        Mockito.reset(workTestServer)
        productsRepository = ProductsRepositoryImpl(workTestServer, netMonitor)
    }

    @Test
    fun `should return mapped categories`() = runTest {
        mockNetMonitor<List<Category>>()
        Mockito.`when`(workTestServer.categories()).thenReturn(fakeCategoriesDto)
        val actual = productsRepository.getCategories()
        Assert.assertEquals(fakeCategories, actual)
    }

    @Test
    fun `get products with tags and merge them`() = runTest {
        Mockito.`when`(workTestServer.products()).thenReturn(listOf(fakeProductDto))
        Mockito.`when`(workTestServer.tags()).thenReturn(fakeTagDtos)
        mockNetMonitor<List<Product>>()
        Mockito.`when`(netMonitor.doOnAvailable<List<Product>>(any())).thenAnswer { invocation ->
            runBlocking {
                val block = invocation.getArgument<suspend () -> List<Product>>(0)
                block()
            }
        }

        val actual = productsRepository.getProducts()
        Assert.assertEquals(listOf(fakeProduct), actual)
    }

    private suspend fun <T> mockNetMonitor() {
        Mockito.`when`(netMonitor.doOnAvailable<T>(any())).thenAnswer { invocation ->
            runBlocking {
                val block = invocation.getArgument<suspend () -> T>(0)
                block()
            }
        }
    }

}