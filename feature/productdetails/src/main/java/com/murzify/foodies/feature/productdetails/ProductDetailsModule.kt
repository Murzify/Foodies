package com.murzify.foodies.feature.productdetails

import com.murzify.foodies.feature.productdetails.components.DefaultProductDetailsComponent
import com.murzify.foodies.feature.productdetails.components.ProductDetailsComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ProductDetailsModule {

    @Binds
    abstract fun bindProductDetailsComponentFactory(
        productDetailsFactory: DefaultProductDetailsComponent.Factory
    ): ProductDetailsComponent.Factory
}