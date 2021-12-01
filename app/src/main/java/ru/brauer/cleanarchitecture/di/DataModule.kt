package ru.brauer.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.*
import ru.brauer.cleanarchitecture.model.repository.Repository
import ru.brauer.cleanarchitecture.model.repository.RepositoryImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    @Named(NAMED_INJECTION_REMOTE_REPO)
    fun repositoryRemote(@Named(NAMED_INJECTION_REMOTE_DATASOURCE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Singleton
    @Provides
    @Named(NAMED_INJECTION_LOCAL_REPO)
    fun repositoryLocal(@Named(NAMED_INJECTION_LOCAL_DATASOURCE) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Singleton
    @Provides
    @Named(NAMED_INJECTION_REMOTE_DATASOURCE)
    fun dataSourceRemote(remoteProvider: RetrofitImplementation): DataSource<List<DataModel>> =
        DataSourceRemote(remoteProvider)

    @Singleton
    @Provides
    fun remoteProvider(): RetrofitImplementation = RetrofitImplementation()

    @Singleton
    @Provides
    @Named(NAMED_INJECTION_LOCAL_DATASOURCE)
    fun dataSourceLocal(localProvider: RoomDataBaseImplementation): DataSource<List<DataModel>> =
        DataSourceLocal(localProvider)

    @Singleton
    @Provides
    fun localProvider(): RoomDataBaseImplementation = RoomDataBaseImplementation()

    companion object {
        const val NAMED_INJECTION_REMOTE_REPO = "remoteRepo"
        const val NAMED_INJECTION_LOCAL_REPO = "localRepo"
        private const val NAMED_INJECTION_REMOTE_DATASOURCE = "remote"
        private const val NAMED_INJECTION_LOCAL_DATASOURCE = "local"
    }
}