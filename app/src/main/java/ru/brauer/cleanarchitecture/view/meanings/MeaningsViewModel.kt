package ru.brauer.cleanarchitecture.view.meanings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.data.Meanings
import javax.inject.Inject

class MeaningsViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val _liveData: MutableLiveData<List<Meanings>> = MutableLiveData()
    val liveData: LiveData<List<Meanings>> get() = _liveData

    fun getData(param: DataModel) {
        param.meanings
            ?.let { _ ->
                compositeDisposable.add(
                    Observable.just(param.meanings)
                        .subscribe {
                            _liveData.postValue(it)
                        }
                )
            }
    }
}