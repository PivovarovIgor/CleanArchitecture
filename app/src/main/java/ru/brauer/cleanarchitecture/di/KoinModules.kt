package ru.brauer.cleanarchitecture.di

import androidx.room.Room
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.brauer.cleanarchitecture.interactor.Interactor
import ru.brauer.cleanarchitecture.model.data.AppState
import ru.brauer.cleanarchitecture.model.data.DataModel
import ru.brauer.cleanarchitecture.model.datasource.DataSourceLocal
import ru.brauer.cleanarchitecture.model.datasource.DataSourceRemote
import ru.brauer.cleanarchitecture.model.datasource.RetrofitImplementation
import ru.brauer.cleanarchitecture.model.datasource.database.AppDataBase
import ru.brauer.cleanarchitecture.model.datasource.database.RoomDataBaseImplementation
import ru.brauer.cleanarchitecture.model.repository.Repository
import ru.brauer.cleanarchitecture.model.repository.RepositoryImplementation
import ru.brauer.cleanarchitecture.model.repository.RepositoryLocal
import ru.brauer.cleanarchitecture.model.repository.RepositoryLocalImplementation
import ru.brauer.cleanarchitecture.rx.ISchedulerProvider
import ru.brauer.cleanarchitecture.rx.SchedulerProvider
import ru.brauer.cleanarchitecture.view.main.MainInteractor
import ru.brauer.cleanarchitecture.view.main.MainViewModel
import ru.brauer.cleanarchitecture.view.meanings.MeaningsViewModel


object DI {

    private const val NAMED_INJECTION_REMOTE_REPO = "remoteRepo"
    private const val NAMED_INJECTION_LOCAL_REPO = "localRepo"
    private const val DATABASE_FILE_NAME = "database.db"

    val mainModule = module {

        single<Repository<List<DataModel>>>(named(NAMED_INJECTION_REMOTE_REPO))
        {
            RepositoryImplementation(
                dataSource = DataSourceRemote(
                    remoteProvider = RetrofitImplementation()
                )
            )
        }


        single { CompositeDisposable() }
        single<ISchedulerProvider> { SchedulerProvider() }
        single<RepositoryLocal> { RepositoryLocalImplementation(get()) }
        single { DataSourceLocal(get()) }
        single { RoomDataBaseImplementation() }
        single {
            Room.databaseBuilder(
                androidContext(),
                AppDataBase::class.java,
                DATABASE_FILE_NAME
            ).build()
        }

        factory<Interactor<AppState>> {
            MainInteractor(
                remoteRepository = get(named(NAMED_INJECTION_REMOTE_REPO)),
                localRepository = get()
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
}