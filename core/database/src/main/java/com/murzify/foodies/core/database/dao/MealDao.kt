package com.murzify.foodies.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murzify.foodies.core.database.model.MealAndCategory
import com.murzify.foodies.core.database.model.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM meal")
    suspend fun getAll(): List<MealEntity>

    @Transaction
    @Query("SELECT * FROM meal WHERE categoryName = :categoryName")
    fun getByCategory(categoryName: String): Flow<List<MealAndCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg category: MealEntity)
}