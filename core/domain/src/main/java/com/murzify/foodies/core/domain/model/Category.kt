package com.murzify.foodies.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Long,
    val name: String
)
