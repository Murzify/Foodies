package com.murzify.gosporttest.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealEntity(
    @PrimaryKey val id: String,
    val name: String,
    val categoryName: String,
    val thumb: String,
    val instructions: String
)
