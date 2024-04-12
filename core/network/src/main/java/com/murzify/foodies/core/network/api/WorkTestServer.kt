package com.murzify.foodies.core.network.api

import com.murzify.foodies.core.network.model.CategoryDto
import com.murzify.foodies.core.network.model.ProductDto
import com.murzify.foodies.core.network.model.TagDto

interface WorkTestServer {
    suspend fun products(): List<ProductDto>

    suspend fun categories(): List<CategoryDto>

    suspend fun tags(): List<TagDto>
}