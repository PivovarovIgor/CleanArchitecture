package ru.brauer.cleanarchitecture.model.datasource

import io.reactivex.rxjava3.core.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}