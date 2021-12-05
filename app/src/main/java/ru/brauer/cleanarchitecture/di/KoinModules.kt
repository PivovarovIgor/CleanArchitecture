package ru.brauer.cleanarchitecture.di

import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.brauer.cleanarchitecture.interactor.Interactor
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.DataSourceLocal
import ru.brauer.cleanarchitecture.model.datasource.DataSourceRemote
import ru.brauer.cleanarchitecture.model.datasource.RetrofitImplementation
import ru.brauer.cleanarchitecture.model.datasource.RoomDataBaseImplementation
import ru.brauer.cleanarchitecture.model.repository.Repository
import ru.brauer.cleanarchitecture.model.repository.RepositoryImplementation
import ru.brauer.cleanarchitecture.rx.ISchedulerProvider
import ru.brauer.cleanarchitecture.rx.SchedulerProvider
import ru.brauer.cleanarchitecture.view.main.MainInteractor
import ru.brauer.cleanarchitecture.view.main.MainViewModel
import ru.brauer.cleanarchitecture.view.meanings.MeaningsViewModel

const val NAMED_INJECTION_REMOTE_REPO = "remoteRepo"
const val NAMED_INJECTION_LOCAL_REPO = "localRepo"

val mainModule = module {

    single<Repository<List<DataModel>>>(named(NAMED_INJECTION_REMOTE_REPO))
    {
        RepositoryImplementation(
            dataSource = DataSourceRemote(
                remoteProvider = RetrofitImplementation()
            )
        )
    }

    single<Repository<List<DataModel>>>(named(NAMED_INJECTION_LOCAL_REPO)) {
        RepositoryImplementation(
            dataSource = DataSourceLocal(
                localProvider = RoomDataBaseImplementation()
            )
        )
    }

    single { CompositeDisposable() }
    single<ISchedulerProvider> { SchedulerProvider() }

    factory<Interactor<AppState>> {
        MainInteractor(
            remoteRepository = get(named(NAMED_INJECTION_REMOTE_REPO)),
            localRepository = get(named(NAMED_INJECTION_LOCAL_REPO))
        )
    }

    viewModel {
        MainViewModel(
            interactor = get(),
            compositeDisposable = get(),
            schedulerProvider = get()
        )
    }

    viewModel { MeaningsViewModel(compositeDisposable = get()) }
}