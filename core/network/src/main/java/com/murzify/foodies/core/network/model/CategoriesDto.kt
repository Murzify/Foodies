package com.murzify.foodies.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDto(
    val categories: List<CategoryDto>
)
