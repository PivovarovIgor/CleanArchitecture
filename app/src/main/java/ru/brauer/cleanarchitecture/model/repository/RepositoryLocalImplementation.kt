package ru.brauer.cleanarchitecture.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.DataSourceLocal

class RepositoryLocalImplementation(private val dataSource: DataSourceLocal) : RepositoryLocal {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
    override fun writeResultsOfSearch(words: List<DataModel>) {
        dataSource.writeResultsOfSearch(words)
    }

    override fun writeHistoryOfSearching(searchWord: String) {
        dataSource.writeHistoryOfSearching(searchWord)
    }
}