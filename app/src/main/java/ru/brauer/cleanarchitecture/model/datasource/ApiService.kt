package ru.brauer.cleanarchitecture.model.datasource

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.brauer.appcore.model.data.DataModel

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}