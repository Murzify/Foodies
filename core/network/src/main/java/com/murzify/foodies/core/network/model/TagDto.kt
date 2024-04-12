package com.murzify.foodies.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    val id: Int,
    val name: String
)
