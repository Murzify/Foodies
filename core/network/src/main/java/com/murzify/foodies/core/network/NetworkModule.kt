package com.murzify.foodies.core.network

import com.murzify.foodies.core.network.api.WorkTestServer
import com.murzify.foodies.core.network.impl.WorkTestServerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindMealDb(
        theMealDbImpl: WorkTestServerImpl
    ): WorkTestServer
}