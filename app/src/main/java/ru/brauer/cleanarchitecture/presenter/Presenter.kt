package ru.brauer.cleanarchitecture.presenter

import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}