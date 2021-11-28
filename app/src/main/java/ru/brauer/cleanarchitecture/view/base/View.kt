package ru.brauer.cleanarchitecture.view.base

import ru.brauer.cleanarchitecture.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}