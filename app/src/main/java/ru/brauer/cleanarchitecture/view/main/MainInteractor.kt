package ru.brauer.cleanarchitecture.view.main

import io.reactivex.rxjava3.core.Observable
import ru.brauer.cleanarchitecture.di.DataModule.Companion.NAMED_INJECTION_LOCAL_REPO
import ru.brauer.cleanarchitecture.di.DataModule.Companion.NAMED_INJECTION_REMOTE_REPO
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.repository.Repository
import ru.brauer.cleanarchitecture.interactor.Interactor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAMED_INJECTION_REMOTE_REPO) private val remoteRepository: Repository<List<DataModel>>,
    @Named(NAMED_INJECTION_LOCAL_REPO) private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}