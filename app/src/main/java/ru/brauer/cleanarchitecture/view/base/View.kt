package ru.brauer.cleanarchitecture.view.base

interface View<T> {
    fun renderData(appState: T)
}