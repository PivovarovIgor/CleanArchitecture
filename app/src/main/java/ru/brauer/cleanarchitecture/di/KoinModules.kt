package ru.brauer.cleanarchitecture.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
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

    private const val DATABASE_FILE_NAME = "database.db"

    val mainModule = module {

        single<Repository<List<DataModel>>>
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

            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("CREATE TABLE search_history (data_time INTEGER NOT NULL, search_word TEXT NOT NULL, PRIMARY KEY(data_time))")
                }
            }

            Room.databaseBuilder(
                androidContext(),
                AppDataBase::class.java,
                DATABASE_FILE_NAME
            ).addMigrations(MIGRATION_1_2)
                .build()
        }

        factory<Interactor<AppState>> {
            MainInteractor(
                remoteRepository = get(),
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