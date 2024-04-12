package com.murzify.foodies.core.data

import com.murzify.foodies.core.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMealDbRepository(
        mealDbRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}