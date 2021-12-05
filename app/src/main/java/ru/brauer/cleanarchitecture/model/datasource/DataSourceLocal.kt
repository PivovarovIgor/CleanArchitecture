package ru.brauer.cleanarchitecture.model.datasource

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.database.RoomDataBaseImplementation

class DataSourceLocal(private val localProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        localProvider.getData(word)

    fun writeData(words: List<DataModel>) =
        localProvider.writeData(words)
}