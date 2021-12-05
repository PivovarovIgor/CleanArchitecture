package ru.brauer.cleanarchitecture.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.DataSourceLocal

class RepositoryLocalImplementation(private val dataSource: DataSourceLocal) : RepositoryLocal {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
    override fun writeData(words: List<DataModel>) {
        dataSource.writeData(words)
    }
}