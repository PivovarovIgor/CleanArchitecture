package ru.brauer.cleanarchitecture.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.brauer.cleanarchitecture.presenter.Presenter

abstract class BaseActivity<T> : AppCompatActivity(), View<T> {

    protected lateinit var presenter: Presenter<T, View<T>>

    protected abstract fun createPresenter(): Presenter<T, View<T>>

    abstract override fun renderData(appState: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}