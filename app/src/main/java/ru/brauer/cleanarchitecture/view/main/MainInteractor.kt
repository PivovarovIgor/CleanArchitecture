package ru.brauer.cleanarchitecture.view.main

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.interactor.Interactor
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.repository.Repository
import ru.brauer.cleanarchitecture.model.repository.RepositoryLocal

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word)
                .map {
                    localRepository.writeResultsOfSearch(it)
                    AppState.Success(it)
                }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}