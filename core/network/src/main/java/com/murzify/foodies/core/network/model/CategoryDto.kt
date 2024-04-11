package com.murzify.foodies.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val idCategory: String,
    val strCategory: String,
)
