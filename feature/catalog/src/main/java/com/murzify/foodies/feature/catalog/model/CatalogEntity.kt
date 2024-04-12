package com.murzify.foodies.feature.catalog.model

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.statekeeper.SerializableContainer
import com.murzify.foodies.feature.catalog.components.CatalogComponent.State

class CatalogEntity(savedState: SerializableContainer?) : InstanceKeeper.Instance {
    var state: State = savedState?.consume(strategy = State.serializer()) ?: State()
        private set

    fun saveState(): SerializableContainer =
        SerializableContainer(value = state, strategy = State.serializer())
}