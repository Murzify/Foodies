package com.murzify.gosporttest.feature.menu

import com.murzify.gosporttest.feature.menu.components.DefaultMenuComponent
import com.murzify.gosporttest.feature.menu.components.MenuComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MenuModule {

    @Binds
    abstract fun bindMenuComponentFactory(
        menuComponentFactory: DefaultMenuComponent.Factory
    ): MenuComponent.Factory
}