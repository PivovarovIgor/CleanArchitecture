package ru.brauer.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import ru.brauer.cleanarchitecture.interactor.Interactor
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.repository.Repository
import ru.brauer.cleanarchitecture.view.main.MainInteractor
import javax.inject.Named
import javax.inject.Singleton

@Module
class InteractorModule {

    @Singleton
    @Provides
    fun mainInteractor(
        @Named("remoteRepo") repositoryRemote: Repository<List<DataModel>>,
        @Named("localRepo") repositoryLocal: Repository<List<DataModel>>
    ): Interactor<AppState> = MainInteractor(repositoryRemote, repositoryLocal)
}