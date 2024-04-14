package com.murzify.foodies.feature.cart

import com.murzify.foodies.feature.cart.components.CartComponent
import com.murzify.foodies.feature.cart.components.DefaultCartComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class CartModule {

    @Binds
    abstract fun bindCartComponentFactory(
        cartComponentFactory: DefaultCartComponent.Factory
    ): CartComponent.Factory
}