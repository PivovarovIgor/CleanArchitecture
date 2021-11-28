package ru.brauer.cleanarchitecture.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.model.datasource.DataSource
import ru.brauer.cleanarchitecture.model.data.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}