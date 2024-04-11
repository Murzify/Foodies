package com.murzify.foodies.feature.catalog

import com.murzify.foodies.feature.catalog.components.CatalogComponent
import com.murzify.foodies.feature.catalog.components.DefaultCatalogComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class CatalogModule {

    @Binds
    abstract fun bindCatalogComponentFactory(
        menuComponentFactory: DefaultCatalogComponent.Factory
    ): CatalogComponent.Factory
}