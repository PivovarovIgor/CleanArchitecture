package ru.brauer.cleanarchitecture.view.meanings

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.data.Meanings
import ru.brauer.cleanarchitecture.presenter.Presenter
import ru.brauer.cleanarchitecture.view.base.View

class MeaningsPresenterImpl<T : List<Meanings>, V : View<T>>(
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : Presenter<List<Meanings>, View<List<Meanings>>> {

    private var currentView: View<List<Meanings>>? = null

    override fun attachView(view: View<List<Meanings>>) {
        currentView = view
    }

    override fun detachView(view: View<List<Meanings>>) {
        if (view == currentView) {
            currentView = null
        }
    }

    fun getData(param: DataModel) {
        param.meanings
            ?.let { _ ->
                compositeDisposable.add(
                    Observable.just(param.meanings)
                        .subscribe {
                            currentView?.renderData(it)
                        }
                )
            }
    }
}