package com.murzify.gosporttest.core.data

import com.murzify.gosporttest.core.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMealDbRepository(
        mealDbRepositoryImpl: MealRepositoryImpl
    ): MealRepository
}