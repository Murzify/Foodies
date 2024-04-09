package com.murzify.gosporttest.core.network

import com.murzify.gosporttest.core.network.api.TheMealDb
import com.murzify.gosporttest.core.network.impl.TheMealDbImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindMealDb(
        theMealDbImpl: TheMealDbImpl
    ): TheMealDb
}