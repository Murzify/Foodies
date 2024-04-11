package com.murzify.foodies.core.domain.model

data class Meal(
    val id: String,
    val name: String,
    val category: Category,
    val thumb: String,
    val instructions: String
)
