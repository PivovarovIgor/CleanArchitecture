package ru.brauer.cleanarchitecture.view.main

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.model.datasource.DataSourceLocal
import ru.brauer.cleanarchitecture.model.datasource.DataSourceRemote
import ru.brauer.cleanarchitecture.model.repository.RepositoryImplementation
import ru.brauer.cleanarchitecture.presenter.Presenter
import ru.brauer.cleanarchitecture.rx.ISchedulerProvider
import ru.brauer.cleanarchitecture.rx.SchedulerProvider
import ru.brauer.cleanarchitecture.view.base.View

class MainPresenterImpl<T : AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: ISchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {
    private var currentView: V? = null
    override fun attachView(view: V) {
        currentView = view
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {

            }
        }
    }
}