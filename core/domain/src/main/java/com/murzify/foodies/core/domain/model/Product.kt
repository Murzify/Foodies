package com.murzify.foodies.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Long,
    val measure: Int,
    val measureUnit: String,
    val energy: Float,
    val proteins: Float,
    val fats: Float,
    val carbohydrates: Float,
    val tags: List<Tag>
)
