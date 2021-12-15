package ru.brauer.cleanarchitecture.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.brauer.appcore.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}