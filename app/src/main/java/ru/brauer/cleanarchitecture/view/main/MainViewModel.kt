package ru.brauer.cleanarchitecture.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.interactor.Interactor
import ru.brauer.cleanarchitecture.rx.ISchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: Interactor<AppState>,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: ISchedulerProvider
) : ViewModel() {

    private val _liveData: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState> get() = _liveData

    fun getData(word: String, isRemote: Boolean) {
        compositeDisposable.add(
        interactor.getData(word, isRemote)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.io())
            .doOnSubscribe { _liveData.postValue(AppState.Loading(null)) }
            .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                _liveData.postValue(appState)
            }

            override fun onError(e: Throwable) {
                _liveData.postValue(AppState.Error(e))
            }

            override fun onComplete() {

            }
        }
    }
}