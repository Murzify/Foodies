package com.murzify.gosporttest.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class MealAndCategory(
    @Embedded val meal: MealEntity,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "name"
    )
    val category: CategoryEntity
)
