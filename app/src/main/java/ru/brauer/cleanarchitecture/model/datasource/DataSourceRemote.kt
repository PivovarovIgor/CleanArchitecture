package ru.brauer.cleanarchitecture.model.datasource

import io.reactivex.rxjava3.core.Observable
import ru.brauer.appcore.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}