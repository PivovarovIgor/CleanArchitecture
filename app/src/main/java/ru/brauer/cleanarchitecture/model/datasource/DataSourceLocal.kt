package ru.brauer.cleanarchitecture.model.datasource

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.model.data.DataModel

class DataSourceLocal(private val localProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        localProvider.getData(word)
}