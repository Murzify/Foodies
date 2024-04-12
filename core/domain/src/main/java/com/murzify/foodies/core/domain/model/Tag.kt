package com.murzify.foodies.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Int,
    val name: String
)
