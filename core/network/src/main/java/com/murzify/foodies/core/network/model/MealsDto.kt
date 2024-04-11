package com.murzify.foodies.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class MealsDto(
    val meals: List<MealDto>
)
