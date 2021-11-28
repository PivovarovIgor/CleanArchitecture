package ru.brauer.cleanarchitecture.model.datasource

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}