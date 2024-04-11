package com.murzify.foodies.feature.menu

import com.murzify.foodies.feature.menu.components.DefaultMenuComponent
import com.murzify.foodies.feature.menu.components.MenuComponent
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