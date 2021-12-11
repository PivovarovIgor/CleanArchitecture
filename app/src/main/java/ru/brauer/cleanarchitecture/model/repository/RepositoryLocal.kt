package ru.brauer.cleanarchitecture.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.model.data.DataModel

interface RepositoryLocal {
    fun getData(word: String): Observable<List<DataModel>>
    fun writeResultsOfSearch(words: List<DataModel>)
    fun writeHistoryOfSearching(searchWord: String)
}