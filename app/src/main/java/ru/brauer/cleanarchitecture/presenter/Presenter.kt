package ru.brauer.cleanarchitecture.presenter

import ru.brauer.cleanarchitecture.view.base.View

interface Presenter<T , V : View<T>> {

    fun attachView(view: V)
    fun detachView(view: V)
}