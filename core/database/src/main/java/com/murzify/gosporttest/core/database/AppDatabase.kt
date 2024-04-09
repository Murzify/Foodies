package com.murzify.gosporttest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.murzify.gosporttest.core.database.dao.CategoryDao
import com.murzify.gosporttest.core.database.dao.MealDao
import com.murzify.gosporttest.core.database.model.CategoryEntity
import com.murzify.gosporttest.core.database.model.MealEntity

@Database(entities = [MealEntity::class, CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao

    abstract fun categoryDao(): CategoryDao

}