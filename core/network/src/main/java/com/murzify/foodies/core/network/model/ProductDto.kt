package com.murzify.foodies.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Long,
    @SerialName("category_id")
    val categoryId: Long,
    val name: String,
    val description: String,
    val image: String,
    @SerialName("price_current")
    val priceCurrent: Long,
    val measure: Int,
    @SerialName("measure_unit")
    val measureUnit: String,
    @SerialName("energy_per_100_grams")
    val energy: Float,
    @SerialName("proteins_per_100_grams")
    val proteins: Float,
    @SerialName("fats_per_100_grams")
    val fats: Float,
    @SerialName("carbohydrates_per_100_grams")
    val carbohydrates: Float,
    @SerialName("tag_ids")
    val tagIds: List<Int>
)
